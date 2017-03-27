DROP DATABASE IF EXISTS `${parentArtifactId}`;
CREATE DATABASE `${parentArtifactId}` default charset=utf8 ;
use `${parentArtifactId}`;


delete from mysql.user where User = '${parentArtifactId}_user';
grant all on `${parentArtifactId}`.* to '${parentArtifactId}_user'@'%' identified by '${parentArtifactId}_pwd';
grant all on `${parentArtifactId}`.* to '${parentArtifactId}_user'@'localhost' identified by '${parentArtifactId}_pwd';
flush privileges;