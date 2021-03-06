package top.auzqy.comment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * description:  项目启动类
 * createTime: 2019-12-26 23:43
 *
 * @author au
 */
@SpringBootApplication(scanBasePackages = "top.auzqy.comment")
@MapperScan("top.auzqy.comment.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
public class PublicCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicCommentApplication.class, args);
    }

}
