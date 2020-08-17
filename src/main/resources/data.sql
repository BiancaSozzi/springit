INSERT INTO link (id,created_at,modified_at,created_by,modified_by,title,url)
VALUES (1,NOW(),NOW(),null,null,'Getting Started with Spring Boot 2','https://therealdanvega.com/spring-boot-2');

INSERT INTO comment
(id,created_by,created_at,modified_by,modified_at,body,link_id)
VALUES
(1,null,NOW(),null,NOW(),'What an awesome idea for a course!',1);