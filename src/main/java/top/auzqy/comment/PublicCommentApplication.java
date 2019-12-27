package top.auzqy.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description:  项目启动类
 * createTime: 2019-12-26 23:43
 * @author au
 */
@SpringBootApplication(scanBasePackages = "top.auzqy.comment")
public class PublicCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicCommentApplication.class, args);
    }

}