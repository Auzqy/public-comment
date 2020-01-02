## java 整合 es

### 接入调试
开始编写java代码
介绍接入elasticsearch的方式

http 客户端（rest client）：通过http方式访问elasticsearch
节点客户端（node client） 以集群中一个节点的方式加入客户端
传输客户端（transport client） 不加入集群，但支持传输协议，解决接入问题
推荐使用rest client，
先接入最基本的名字查询

```
@Override
public List<ShopModel> searchES(BigDecimal longitude, BigDecimal latitude, String keyword) throws IOException {

    SearchRequest searchRequest = new SearchRequest("shop");

    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.matchQuery("name",keyword));
    sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
    searchRequest.source(sourceBuilder);

    List<Integer> shopIdList = new ArrayList<>();
    SearchResponse response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    SearchHit[] hitses = response.getHits().getHits();
    for(SearchHit hit : hitses){
        shopIdList.add(new Integer(hit.getSourceAsMap().get("id").toString()));
    }

    List<ShopModel> shopModelList = shopIdList.stream().map(id->{
        return get(id);
    }).collect(Collectors.toList());

    return shopModelList;

    //RestClient.builder(new HttpHost("127.0.0.1",9200,"http"));
}
```

然后用restful low api直接写json获取，先设置replace模式，看到花悦***很高，因为没有考虑词因素，加入词因素后，花悦沉底

过于复杂的模型请求可以通过JSONObject拼装请求

```
//加入categoryid过滤
GET /shop/_search
{
  "_source": "*",
  "script_fields":{
    "distance":{
      "script":{
      "source":"haversin(lat, lon, doc['location'].lat, doc['location'].lon)",
      "lang":"expression",
      "params":{"lat":31.24916171,"lon":121.48789949}
      }
    }
  },
  "query":{
    
    "function_score": {
      "query":{
        "bool":{
          "must":[
              {"match": {"name":{"query":"凯悦","boost":0.1}}},
              {"term":{"seller_disabled_flag": 0}},
              {"term":{"category_id": 2}}
            ]
        }
      },

    "functions":[
        {
          "gauss": {
            "location": {
              "origin": "31.24916171, 121.48789949",
              "scale": "100km",
              "offset":"0km",
              "decay": 0.5
            }
          },
          "weight":9
        },
        {
          "field_value_factor": {
          "field": "remark_score"
          },
          "weight":0.2
        },
        {
          "field_value_factor": {
          "field": "seller_remark_score"
          },
          "weight":0.1
        }
      ],
      "score_mode": "sum",
      "boost_mode": "sum"
    }
  },
  "sort": [
    {
      "_score": {
        "order": "desc"
      }
    }
  ]
}
```
//加入低价排序

```
GET /shop/_search
{
  "_source": "*",
  "script_fields":{
    "distance":{
      "script":{
      "source":"haversin(lat, lon, doc['location'].lat, doc['location'].lon)",
      "lang":"expression",
      "params":{"lat":31.24916171,"lon":121.48789949}
      }
    }
  },
  "query":{
    
    "function_score": {
      "query":{
        "bool":{
          "must":[
              {"match": {"name":{"query":"凯悦","boost":0.1}}},
              {"term":{"seller_disabled_flag": 0}},
              {"term":{"category_id": 2}}
            ]
        }
      },

    "functions":[
        {
          "field_value_factor": {
          "field": "price_per_man"
          },
          "weight":1
        }
      ],
      "score_mode": "sum",
      "boost_mode": "replace"
    }
  },
  "sort": [
    {
      "_score": {
        "order": "asc"
      }
    }
  ]
}
```

//开始集成aggs，可以看到默认使用whitespace分词了
```
GET /shop/_search
{
  "_source": "*",
  "script_fields":{
    "distance":{
      "script":{
      "source":"haversin(lat, lon, doc['location'].lat, doc['location'].lon)",
      "lang":"expression",
      "params":{"lat":31.24916171,"lon":121.48789949}
      }
    }
  },
  "query":{
    
    "function_score": {
       "query":{
        "bool":{
          "must":[
              {"match": {"name":{"query":"凯悦","boost":0.1}}},
              {"term":{"seller_disabled_flag": 0}},
              {"term":{"tags": "落地大窗"}}
            ]
        }

      },

    "functions":[
        {
          "gauss": {
            "location": {
              "origin": "31.24916171, 121.48789949",
              "scale": "100km",
              "offset":"0km",
              "decay": 0.5
            }
          },
          "weight":9
        },
        {
          "field_value_factor": {
          "field": "remark_score"
          },
          "weight":0.2
        },
        {
          "field_value_factor": {
          "field": "seller_remark_score"
          },
          "weight":0.1
        }
      ],
      "score_mode": "sum",
      "boost_mode": "sum"
    }
  },
  "sort": [
    {
      "_score": {
        "order": "desc"
      }
    }
  ],
  "aggs":{
    "group_by_tags":{
      "terms":{
        "field":"tags"
      }
    }
  }
}
```