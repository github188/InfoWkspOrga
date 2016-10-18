/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.23 : Database - info_wksp_orga
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`info_wksp_orga` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `info_wksp_orga`;

/*Table structure for table `i18n` */

DROP TABLE IF EXISTS `i18n`;

CREATE TABLE `i18n` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `language` char(2) DEFAULT 'fr' COMMENT 'language = ''fr'' by default',
  `value` varchar(255) DEFAULT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_i18n_Biz_PK` (`name`,`language`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Manage Business data internationalization';

/*Data for the table `i18n` */

/*Table structure for table `lock` */

DROP TABLE IF EXISTS `lock`;

CREATE TABLE `lock` (
  `id` int(11) NOT NULL,
  `entity_name` varchar(100) NOT NULL COMMENT 'The java entity name without Package = Table name',
  `entity_id` int(11) NOT NULL COMMENT 'Id of the locked entity = Table Id',
  `max_duration` int(11) NOT NULL DEFAULT '10' COMMENT 'in second. -1 = infinite',
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_lock_Biz_PK` (`entity_name`,`entity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Manage Table entity lock';

/*Data for the table `lock` */

/*Table structure for table `perspective` */

DROP TABLE IF EXISTS `perspective`;

CREATE TABLE `perspective` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT 'code/nom permettant d''identifier ce modéle',
  `icon` varchar(255) DEFAULT NULL COMMENT 'icone permettant d''identifier ce modéle',
  `description` varchar(255) DEFAULT NULL,
  `owner` varchar(45) NOT NULL COMMENT 'Généralement identique au createdBy. Correspond à la personne habilitée a gérer cette organisation de workspaces',
  `partage` enum('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PUBLIC' COMMENT 'Private correspond à une visibilité au créateur (propriétaire) ',
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `order` int(11) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_perspective_Biz_PK` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cette table permet de définir via un fichier XML la structure de base des workspaces.';

/*Data for the table `perspective` */

insert  into `perspective`(`id`,`name`,`icon`,`description`,`owner`,`partage`,`enabled`,`order`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) values (2,'perspective.gfi.name','/icons/perspective.png','perspective.gfi.description','admin','PUBLIC','',1,'sguisse','2016-09-19 18:10:52',NULL,NULL);

/*Table structure for table `perspective_workspaces` */

DROP TABLE IF EXISTS `perspective_workspaces`;

CREATE TABLE `perspective_workspaces` (
  `id` int(11) NOT NULL,
  `perspective_id` int(11) NOT NULL,
  `workspace_id` varchar(11) NOT NULL,
  `workspace_order` int(11) DEFAULT NULL COMMENT 'Le numéro d''ordre d''apparition du workspace dans la hierarchie de la perspective',
  `created_by` varchar(45) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier',
  `last_modified_date` timestamp NULL DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier',
  PRIMARY KEY (`id`),
  UNIQUE KEY `perspective_id` (`perspective_id`,`workspace_id`),
  KEY `FK_Workspace` (`workspace_id`),
  CONSTRAINT `FK_Perspective` FOREIGN KEY (`perspective_id`) REFERENCES `perspective` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Workspace` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Manage Workspaces attached to Perspective';

/*Data for the table `perspective_workspaces` */

insert  into `perspective_workspaces`(`id`,`perspective_id`,`workspace_id`,`workspace_order`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) values (1036,2,'1',1,'sguisse','2016-09-19 18:10:52',NULL,NULL),(1037,2,'2',2,'sguisse','2016-09-19 18:10:52',NULL,NULL),(1038,2,'sguisse',3,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 17:13:22'),(1039,2,'3',6,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 17:13:22'),(1040,2,'4',4,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 17:13:22'),(1041,2,'5',5,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 17:13:22'),(1042,2,'6',13,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1043,2,'7',14,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1044,2,'8',15,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1045,2,'9',16,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1046,2,'10',17,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1047,2,'11',18,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1048,2,'12',19,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1049,2,'13',20,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1050,2,'14',21,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1051,2,'15',22,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1052,2,'16',23,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25'),(1053,2,'17',24,'sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 09:04:25');

/*Table structure for table `preference` */

DROP TABLE IF EXISTS `preference`;

CREATE TABLE `preference` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT 'Le node parent de cette preference, permet de construire un arbre de propriété (cf preferences Eclipse)',
  `name` varchar(250) NOT NULL COMMENT 'La clé de la preference',
  `description` varchar(500) DEFAULT NULL,
  `category` varchar(100) NOT NULL DEFAULT 'DEFAULT' COMMENT 'On peut voir la ''category'' comme un  groupe. Regroupement d''une même famille de propriétés (ex: Adresse; Personne; ...) au sein d''un même node',
  `type` enum('TREE_NODE','OBJECT','STRING','DESCRIPTION','PASSWORD','BOOLEAN','INTEGER','DOUBLE','DATE','TIME','DATE_TIME','LIST','COLOR') NOT NULL DEFAULT 'STRING' COMMENT 'Le type de donnée (int, string, object, ...) de la preference',
  `value` longtext COMMENT 'La valeur de la preference',
  `constraints` varchar(500) DEFAULT NULL COMMENT 'Liste des constraints (cf régles Drools) separated by ;',
  `updatable` bit(1) NOT NULL DEFAULT b'1' COMMENT 'Flag qui indique si on peut mettre à jour la preference',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT 'Flag qui indique si il faut prendre en compte la preference',
  `order` int(11) NOT NULL COMMENT 'Index d''affichage de la préférence',
  `localization` enum('DATABASE','LOCAL_PROPERTIES_FILE') DEFAULT NULL COMMENT 'Indique où est définie la préférence: en base ou dans un fichier properties',
  `owner` varchar(45) NOT NULL COMMENT 'Généralement identique au createdBy. Correspond à la personne habilitée a gérer cette organisation de workspaces',
  `partage` enum('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PUBLIC' COMMENT 'Private correspond à une visibilité au créateur (propriétaire)',
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_preference_Biz_PK` (`category`,`name`),
  KEY `FK_parent_preference_IDX` (`parent_id`),
  CONSTRAINT `FK_206blj3fucctm7hpacm9kwnmx` FOREIGN KEY (`parent_id`) REFERENCES `preference` (`id`),
  CONSTRAINT `FK_SELF_PARENT` FOREIGN KEY (`parent_id`) REFERENCES `preference` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Manage application preferences';

/*Data for the table `preference` */

insert  into `preference`(`id`,`parent_id`,`name`,`description`,`category`,`type`,`value`,`constraints`,`updatable`,`enabled`,`order`,`localization`,`owner`,`partage`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) values (1,NULL,'preferences.tree.root.text',NULL,'Default','TREE_NODE',NULL,NULL,'\0','\0',1,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(2,1,'preferences.tree.generales.text','preferences.tree.generales.text.tooltip','Default','TREE_NODE',NULL,NULL,'\0','\0',2,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(3,2,'preferences.tree.user.text','preferences.tree.user.tooltip','Default','TREE_NODE',NULL,'','\0','\0',3,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(4,3,'preferences.tree.user.local.preference.location.text','preferences.tree.user.local.preference.location.tooltip','default','STRING','$userHome$/Application Data/$appName$/$appVersion$/$appName$-settings.properties','','\0','\0',4,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(5,3,'preferences.tree.user.session.text','preferences.tree.user.session.tooltip','Default','TREE_NODE',NULL,'','\0','\0',5,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(6,5,'preferences.tree.user.session.timeout.text','preferences.tree.user.session.timeout.tooltip','default','INTEGER','3600','','\0','\0',6,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(7,5,'preferences.tree.user.nb.connection.try.max.text','preferences.tree.user.nb.connection.try.max.tooltip','default','INTEGER','3','','\0','\0',7,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(8,5,'preferences.tree.user.session.is.captcha.needed.text','preferences.tree.user.session.is.captcha.needed.tooltip','default','BOOLEAN','false','','\0','\0',8,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(9,2,'preferences.tree.network.connexion.text','preferences.tree.network.connexion.tooltip','Default','TREE_NODE',NULL,'','\0','\0',9,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(10,9,'preferences.tree.network.connexion.proxy.text','preferences.tree.network.connexion.proxy.tooltip','Default','TREE_NODE','3600','','\0','\0',10,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(11,10,'preferences.tree.network.connexion.proxy.no.proxy.text','preferences.tree.network.connexion.proxy.no.proxy.tooltip','proxy.type.to.use','BOOLEAN','3','','\0','\0',11,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(12,10,'preferences.tree.network.connexion.proxy.system.text','preferences.tree.network.connexion.proxy.system.tooltip','proxy.type.to.use','BOOLEAN','3','','\0','\0',12,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(13,10,'preferences.tree.network.connexion.proxy.manual.text','preferences.tree.network.connexion.proxy.manual.tooltip','proxy.type.to.use','BOOLEAN','3','','\0','\0',13,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(14,10,'preferences.tree.network.connexion.proxy.http.proxy.ip.text','preferences.tree.network.connexion.proxy.http.proxy.ip.tooltip','proxy.type.manual','STRING','false','','\0','\0',14,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(15,10,'preferences.tree.network.connexion.proxy.http.proxy.port.text','preferences.tree.network.connexion.proxy.http.proxy.port.tooltip','proxy.type.manual','INTEGER','3','','\0','\0',15,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(16,10,'preferences.tree.network.connexion.proxy.ssl.proxy.ip.text','preferences.tree.network.connexion.proxy.ssl.proxy.ip.tooltip','proxy.type.manual','STRING','false','','\0','\0',16,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(17,10,'preferences.tree.network.connexion.proxy.ssl.proxy.port.text','preferences.tree.network.connexion.proxy.ssl.proxy.port.tooltip','proxy.type.manual','INTEGER','3','','\0','\0',17,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(18,10,'preferences.tree.network.connexion.proxy.ftp.proxy.ip.text','preferences.tree.network.connexion.proxy.ftp.proxy.ip.tooltip','proxy.type.manual','STRING','false','','\0','\0',18,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(19,10,'preferences.tree.network.connexion.proxy.ftp.proxy.port.text','preferences.tree.network.connexion.proxy.ftp.proxy.port.tooltip','proxy.type.manual','INTEGER','3','','\0','\0',19,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(20,10,'preferences.tree.network.connexion.proxy.socks.host.ip.text','preferences.tree.network.connexion.proxy.socks.host.ip.tooltip','proxy.type.manual','STRING','false','','\0','\0',20,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(21,10,'preferences.tree.network.connexion.proxy.socks.host.port.text','preferences.tree.network.connexion.proxy.socks.host.port.tooltip','proxy.type.manual','INTEGER','3','','\0','\0',21,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(22,10,'preferences.tree.network.connexion.proxy.sock.v4.text','preferences.tree.network.connexion.proxy.sock.v4.tooltip','proxy.type.manual','BOOLEAN','3','','\0','\0',22,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(23,10,'preferences.tree.network.connexion.proxy.sock.v5.text','preferences.tree.network.connexion.proxy.sock.v5.tooltip','proxy.type.manual','BOOLEAN','3','','\0','\0',23,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(24,10,'preferences.tree.network.connexion.proxy.remote.dns.text','preferences.tree.network.connexion.proxy.remote.dns.tooltip','proxy.type.manual','BOOLEAN','3','','\0','\0',24,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL),(25,10,'preferences.tree.network.connexion.proxy.no.proxy.for.text','preferences.tree.network.connexion.proxy.no.proxy.for.tooltip','proxy.type.manual','STRING','3','','\0','\0',25,'DATABASE','admin','PUBLIC','admin','2016-09-08 13:56:33',NULL,NULL);

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT 'Le node parent de cette projet, permet de construire un arbre de projet ayant des liens entre eux',
  `name` varchar(50) NOT NULL COMMENT 'Correspond au code projet',
  `description` varchar(255) DEFAULT NULL COMMENT 'Décrit l''objectif visé par la création de ce projet (suivi d''une affaire, d''un projet informatique, suivi de ses comptes personnels, Faciliter l''accés aux outils,...)',
  `category` varchar(45) NOT NULL COMMENT 'Groupe d''appartenance de ce projet (ex : Projet pour BNPP, Projet pour SNCF, Projet personnel) ==> BNPP; SNCF; Personnel',
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `owner` varchar(45) NOT NULL COMMENT 'Généralement identique au createdBy. Correspond à la personne habilitée a gérer cette organisation de workspaces',
  `partage` enum('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PRIVATE' COMMENT 'Private correspond à une visibilité au créateur (propriétaire) du workspace',
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` tinyint(4) NOT NULL,
  `groupe` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_project_Biz_PK` (`name`,`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Manage workspace organization. It specify a special node to create workspaces for it';

/*Data for the table `project` */

insert  into `project`(`id`,`parent_id`,`name`,`description`,`category`,`enabled`,`owner`,`partage`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`enable`,`groupe`) values (0,NULL,'Default','Default Project','Default','','','PRIVATE','sguisse','2016-08-23 03:47:29',NULL,NULL,0,''),(1,NULL,'sguisse','My personnal project','sguisse','','','PRIVATE','sguisse','2016-07-11 14:03:27',NULL,NULL,0,'');

/*Table structure for table `project_configuration` */

DROP TABLE IF EXISTS `project_configuration`;

CREATE TABLE `project_configuration` (
  `id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `category` varchar(45) NOT NULL COMMENT 'Regroupement d''une même famille de propriétés (ex: Adresse; Personne; ...)',
  `name` varchar(50) NOT NULL COMMENT 'La cle de la preference',
  `type` varchar(50) NOT NULL COMMENT 'Le type de donnée (int, string, object, ...) de la preference',
  `value` longtext NOT NULL COMMENT 'La valeur de la preference',
  `constraints` varchar(500) DEFAULT NULL COMMENT 'Liste des constraints (cf régle Drools) separated by ;',
  `updatable` bit(1) NOT NULL DEFAULT b'1',
  `mandatory` bit(1) NOT NULL DEFAULT b'0',
  `order` int(11) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `groupe` varchar(45) NOT NULL,
  `ordre` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_project_configuration_Biz_PK` (`project_id`,`category`,`name`),
  KEY `FK_project_configuration_IDX` (`project_id`),
  CONSTRAINT `FK_fserys4sc7k295w86erainwlv` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_project_configuration` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Permet de définir et afficher les propriétés (informations) principales du projet dans le workspace attaché directement au projet';

/*Data for the table `project_configuration` */

/*Table structure for table `sequence` */

DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
  `name` varchar(100) NOT NULL,
  `increment` int(11) unsigned NOT NULL DEFAULT '1',
  `min_value` int(11) unsigned NOT NULL DEFAULT '1',
  `max_value` bigint(20) unsigned NOT NULL DEFAULT '18446744073709551615',
  `cur_value` bigint(20) unsigned DEFAULT '1000',
  `cycle` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Permet de gérer les id de chacune des tables de l''application. La function définie ''nextval'' prend en param le nom d''une sequence et retourne le nouvel id pour la table associée à cette séquence';

/*Data for the table `sequence` */

insert  into `sequence`(`name`,`increment`,`min_value`,`max_value`,`cur_value`,`cycle`) values ('I18N_SEQ',1,1,18446744073709551615,1000,0),('LOCK_SEQ',1,1,18446744073709551615,1000,0),('PERSPECTIVE_SEQ',1,1,18446744073709551615,1000,0),('WORKSPACE_SEQ',1,1,18446744073709551615,1052,0),('PERSPECTIVE_WORKSPACES_SEQ',1,1,18446744073709551615,1221,0),('VIEW_SEQ',1,1,18446744073709551615,1034,0),('VIEW_ATTRIBUTE_SEQ',1,1,18446744073709551615,1039,0),('PREFERENCE_SEQ',1,1,18446744073709551615,1000,0),('PROJECT_SEQ',1,1,18446744073709551615,1000,0),('PROJECT_CONFIGURATION_SEQ',1,1,18446744073709551615,1000,0),('USER_SEQ',1,1,18446744073709551615,1000,0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `login` varchar(20) NOT NULL,
  `firstname` varchar(30) NOT NULL COMMENT 'Prénom',
  `lastname` varchar(30) NOT NULL COMMENT 'Nom de famille',
  `password` varchar(30) NOT NULL,
  `email_1` varchar(45) NOT NULL COMMENT 'Mail principal de l''utilisateur',
  `email_2` varchar(45) DEFAULT NULL COMMENT 'Mail où on peux joindre le collaborateur chez un client par exemple',
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_user_Biz_PK` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Contient l''ensemble des utilisateurs pouvant accéder à l''application';

/*Data for the table `user` */

insert  into `user`(`id`,`login`,`firstname`,`lastname`,`password`,`email_1`,`email_2`,`enabled`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) values (0,'admin','Sébastien','Guisse','211276','sebastien.guisse@gfi.fr','sebguisse@gmail.com','','sguisse','2016-08-23 03:47:33',NULL,NULL),(1,'sguisse','Sébastien','Guisse','211276','sebastien.guisse@gfi.fr','sebguisse@gmail.com','','sguisse','2016-08-23 03:47:33',NULL,NULL);

/*Table structure for table `user_session` */

DROP TABLE IF EXISTS `user_session`;

CREATE TABLE `user_session` (
  `userId` int(11) NOT NULL,
  `startConnection` datetime NOT NULL,
  `expired` datetime NOT NULL COMMENT 'Calculer grâce à la preference globale : preferences.tree.user.session.timeout.text',
  `lastConnection` datetime NOT NULL,
  `lastServiceCall` datetime DEFAULT NULL,
  `last_nb_cnx_error` int(11) DEFAULT '0' COMMENT 'Permet de stocker de nombre de tentative de connection lors de la dernière ouverture de session',
  `locked` bit(1) DEFAULT b'0' COMMENT 'Permet de savoir si le compte est verrouillé. Dans ce cas il doit effectuer une demande de ré-initialisation de son mot de passe depuis la mire de login.',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_session` */

/*Table structure for table `view` */

DROP TABLE IF EXISTS `view`;

CREATE TABLE `view` (
  `id` int(11) NOT NULL,
  `workspace_id` varchar(11) NOT NULL,
  `name` varchar(45) NOT NULL COMMENT 'Code manuel saisi par l''utilisateur pour l''identification de la vue (regroupement paire Attribute/Value',
  `java_type` varchar(300) NOT NULL COMMENT 'Identifies the View Type (Package with .class name)',
  `icon` varchar(300) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `category` varchar(50) NOT NULL DEFAULT 'Default' COMMENT 'code de regroupement des vues, pour avoir une organisation différente au sein des workspaces de la même société',
  `tags` varchar(500) DEFAULT NULL COMMENT 'Permet d''ajouter des étiquettes (séparées par des '';'') à la vue pour en faciliter la recherche',
  `cmmi_practices` varchar(500) DEFAULT NULL COMMENT 'CMMI relative practices separated by ";"',
  `owner` varchar(45) NOT NULL COMMENT 'Par défaut = au créateur de la vue',
  `partage` enum('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PUBLIC' COMMENT 'Private correspond à une visibilité au créateur (propriétaire) du workspace',
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier, MAJ par Annuler/Remplace',
  `last_modified_date` datetime DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier, MAJ par Annuler/Remplace',
  PRIMARY KEY (`id`),
  KEY `FK_m25k9x3con7ytjgtr2ifqfm9y` (`workspace_id`,`name`),
  CONSTRAINT `FK_Workspace_View` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`),
  CONSTRAINT `FK_nkg9gux0ef03go8pq2xuee7xi` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Contient l''ensemble des vues des workspaces avec leur configuration pour pouvoir être reconstruite lors du chargement du workspace';

/*Data for the table `view` */

insert  into `view`(`id`,`workspace_id`,`name`,`java_type`,`icon`,`description`,`category`,`tags`,`cmmi_practices`,`owner`,`partage`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) values (1031,'3','My Folder','com.sgu.mazeexplorer.swing.views.file.explorer.FileExplorerViewIdentifier',NULL,NULL,'Default',NULL,NULL,'sguisse','PUBLIC','sguisse','2016-09-21 18:05:47',NULL,NULL),(1032,'15','Documents CDSSP','com.sgu.mazeexplorer.swing.views.file.explorer.FileExplorerViewIdentifier',NULL,NULL,'Default',NULL,NULL,'sguisse','PUBLIC','sguisse','2016-09-23 16:05:18','sguisse','2016-09-23 16:33:42'),(1033,'sguisse','My Web site','com.sgu.mazeexplorer.swing.views.web.WebViewIdentifier',NULL,NULL,'Default',NULL,NULL,'sguisse','PUBLIC','sguisse','2016-09-26 11:55:44','sguisse','2016-09-26 11:56:56');

/*Table structure for table `view_attribute` */

DROP TABLE IF EXISTS `view_attribute`;

CREATE TABLE `view_attribute` (
  `id` int(11) NOT NULL,
  `view_id` int(11) NOT NULL COMMENT 'clé d''appartenance des cet attibut à une vue',
  `name` varchar(45) NOT NULL COMMENT 'nom de cet attibut',
  `value` longtext,
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier, MAJ par Annuler/Remplace',
  `last_modified_date` datetime DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier, MAJ par Annuler/Remplace',
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m25k9x3con7ytjgtr2ifqfm9y` (`view_id`),
  CONSTRAINT `FK_View` FOREIGN KEY (`view_id`) REFERENCES `view` (`id`),
  CONSTRAINT `FK_dkke6dl14v4myuqcic5i0hhux` FOREIGN KEY (`view_id`) REFERENCES `view` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Contient l''ensemble des attributs des vues';

/*Data for the table `view_attribute` */

insert  into `view_attribute`(`id`,`view_id`,`name`,`value`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`description`) values (1036,1031,'FILE_EXPLORER_PATH','G:\\sauvegarde\\Tools\\RegExpression','sguisse','2016-09-21 18:05:47',NULL,NULL,NULL),(1037,1032,'FILE_EXPLORER_PATH','G:\\Cloud\\owncloud\\SNCF - CDS-SP','sguisse','2016-09-23 16:05:18','sguisse','2016-09-23 16:33:42',NULL),(1038,1033,'WEB_URL','http://localhost:8080/maze-explorer-prez/?theme=black','sguisse','2016-09-26 11:55:44','sguisse','2016-09-26 11:56:56',NULL);

/*Table structure for table `workspace` */

DROP TABLE IF EXISTS `workspace`;

CREATE TABLE `workspace` (
  `id` varchar(11) NOT NULL COMMENT 'Le workspace est de type varchar pour permettre la creation d''un id avec le login utilisateur',
  `master_id` varchar(11) DEFAULT NULL COMMENT 'Indique que le LAYOUT à utiliser est celui de ce workspace id',
  `parent_id` varchar(11) DEFAULT NULL COMMENT 'Le workspace parent de ce workspace (= rattachement brut)',
  `children_wrksp_creation_enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT 'Indique si ce workspace peut avoir des enfants',
  `project_id` int(11) DEFAULT NULL COMMENT 'L''id de projet associé à ce workspace',
  `base_folder` varchar(300) DEFAULT NULL COMMENT 'contient le repertoire de base pour les vues de type FileExplorer, utile dans le cadre d''1 workspace projet',
  `customer` varchar(100) DEFAULT NULL COMMENT 'le nom du client auquel se rapporte ce workspace, utile dans le cadre d'' workspace projet',
  `name` varchar(100) NOT NULL COMMENT 'contient un code/nom permettant de faciliter la recherche d''un workspace',
  `description` varchar(250) DEFAULT NULL,
  `category` varchar(50) DEFAULT 'Default' COMMENT 'code de regroupement d''une même famille de workspaces',
  `tags` varchar(500) DEFAULT NULL COMMENT 'Permet d''ajouter des étiquettes (séparées par des '';'') au Workspace pour en faciliter la recherche',
  `type` enum('ROOT','CLASSEUR','HELP','USER','ENTREPRISE','PROJECT','TMA','TEMPLATE','GESTION','TOOLS','DOCUMENTATION') NOT NULL DEFAULT 'CLASSEUR',
  `color` varchar(30) NOT NULL DEFAULT 'rgb(0,0,0)' COMMENT 'couleur HTML RGB: 255, 255, 255',
  `bold` bit(1) NOT NULL DEFAULT b'0',
  `strike` bit(1) NOT NULL DEFAULT b'0',
  `italic` bit(1) NOT NULL DEFAULT b'0',
  `underline` bit(1) NOT NULL DEFAULT b'0',
  `icon` varchar(300) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `layout` longtext COMMENT 'Contient le layout du workspace, utilisé pour le reconstruire à l''identique lors de la sauvegarde',
  `owner` varchar(45) NOT NULL COMMENT 'Par défaut = au créateur du Workspace',
  `partage` enum('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PRIVATE' COMMENT 'Private correspond à une visibilité au créateur (propriétaire) du workspace',
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_workspace_Biz_PK` (`name`,`type`),
  KEY `FK_2bxxv70t7w1lvhfpc0o9sph96` (`master_id`),
  KEY `FK_bu7lf9uprf87jil18kqctsjtq` (`parent_id`),
  KEY `FK_pcwlfbims8vpg3nnnoltcapba` (`project_id`),
  CONSTRAINT `FK_2bxxv70t7w1lvhfpc0o9sph96` FOREIGN KEY (`master_id`) REFERENCES `workspace` (`id`),
  CONSTRAINT `FK_MASTER` FOREIGN KEY (`master_id`) REFERENCES `workspace` (`id`),
  CONSTRAINT `FK_PARENT` FOREIGN KEY (`parent_id`) REFERENCES `workspace` (`id`),
  CONSTRAINT `FK_PROJET` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_bu7lf9uprf87jil18kqctsjtq` FOREIGN KEY (`parent_id`) REFERENCES `workspace` (`id`),
  CONSTRAINT `FK_pcwlfbims8vpg3nnnoltcapba` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table de stockage des workspaces.';

/*Data for the table `workspace` */

insert  into `workspace`(`id`,`master_id`,`parent_id`,`children_wrksp_creation_enabled`,`project_id`,`base_folder`,`customer`,`name`,`description`,`category`,`tags`,`type`,`color`,`bold`,`strike`,`italic`,`underline`,`icon`,`enabled`,`layout`,`owner`,`partage`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) values ('1',NULL,NULL,'',NULL,NULL,NULL,'perspective.tree.node.root','perspective.tree.node.root.description','Default',NULL,'ROOT','rgb(0,0,0)','\0','\0','\0','\0','/icons/workspace.png','',NULL,'admin','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('10',NULL,'6','',NULL,NULL,NULL,'perspective.tree.node.documentation.regex','perspective.tree.node.documentation.regex.description','Default',NULL,'DOCUMENTATION','rgb(0,0,0)','\0','\0','\0','\0','/icons/regex.png','',NULL,'sguisse','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('11',NULL,'1','',NULL,NULL,NULL,'Gfi Informatique',NULL,'Default','Gfi','ENTREPRISE','rgb(247,148,29)','','\0','\0','\0','/icons/customer/GFI.png','',NULL,'admin','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('12',NULL,'11','',NULL,NULL,NULL,'Classeurs - RH',NULL,'Default','RH','GESTION','rgb(0, 0, 0)','\0','\0','\0','\0','/icons/Address-book.png','',NULL,'admin','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('13',NULL,'11','\0',NULL,NULL,NULL,'perspective.tree.node.project.template',NULL,'Default','Master, Template','TEMPLATE','rgb(0, 0, 0)','','\0','\0','\0','/icons/template.png','',NULL,'admin','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('14',NULL,'11','',NULL,NULL,NULL,'perspective.tree.node.project',NULL,'Default','init,Project','PROJECT','rgb(0, 0, 0)','','\0','\0','\0','/icons/project.png','',NULL,'admin','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('15',NULL,'14','',NULL,NULL,NULL,'SNCF - CDSSP','L\'objectif du projet et de faciliter la prise en charge des demandes du client tout en respectant le SLA défini au contrat','Default','SNCF,Project','PROJECT','rgb(0, 0, 0)','','\0','\0','\0','/icons/customer/SNCF.png','','<object-stream>\n  <int>4</int>\n  <boolean>true</boolean>\n  <int>1</int>\n  <int>530</int>\n<!-- View definition : BEGIN -->\n  <com.sgu.mazeexplorer.swing.views.file.explorer.FileExplorerViewIdentifier>\n    <viewEntityName>Documents CDSSP</viewEntityName>\n  </com.sgu.mazeexplorer.swing.views.file.explorer.FileExplorerViewIdentifier>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>true</boolean>\n  <string>Title</string>\n  <int>0</int>\n  <string>Documents CDSSP</string>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n<!-- View definition : END -->\n\n  <int>1</int>\n  <int>0</int>\n  <int>1</int>\n  <int>0</int>\n  <int>1</int>\n  <int>1</int>\n  <int>1</int>\n  <int>2</int>\n  <int>0</int>\n  <boolean>true</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <boolean>true</boolean>\n  <boolean>false</boolean>\n  <float>0.33</float>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <float>0.5</float>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <string>Tab Window Properties</string>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <string>Tabbed Panel Properties</string>\n  <boolean>true</boolean>\n  <string>Tab Area Orientation</string>\n  <int>0</int>\n  <net.infonode.util.Direction serialization=\"custom\">\n    <net.infonode.util.Enum>\n      <short>0</short>\n    </net.infonode.util.Enum>\n  </net.infonode.util.Direction>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>true</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>0</int>\n  <int>4</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>0</int>\n  <int>-1</int>\n  <int>-1</int>\n</object-stream>','admin','PUBLIC','sguisse','2016-09-19 18:10:52','sguisse','2016-09-23 16:33:42'),('16',NULL,'14','',NULL,NULL,NULL,'SNCF - CARS/CROSS','L\'objectif du projet et de faciliter la prise en charge des demandes du client tout en respectant le SLA défini au contrat','Default','SNCF,Project','PROJECT','rgb(0, 0, 0)','','\0','\0','\0','/icons/customer/SNCF.png','',NULL,'admin','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('17','5','11','',1,NULL,'BNPP','perspective.tree.node.tma',NULL,'Default','init,TMA','TMA','rgb(0, 0, 0)','','\0','\0','\0','/icons/tma.png','',NULL,'admin','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('2',NULL,'1','',NULL,NULL,NULL,'perspective.tree.node.help','perspective.tree.node.help.description','Default',NULL,'HELP','rgb(0,0,0)','\0','\0','\0','\0','/icons/help.png','',NULL,'admin','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('3',NULL,'4','',NULL,'','','Perso','Pas touche','USER','current-user, moi, perso','USER','rgb(82, 150, 90)','','','\0','\0','http://files.softicons.com/download/system-icons/oxygen-icons-by-oxygen/png/16x16/actions/underconstruction.png','','<object-stream>\n  <int>4</int>\n  <boolean>true</boolean>\n  <int>1</int>\n  <int>518</int>\n<!-- View definition : BEGIN -->\n  <com.sgu.mazeexplorer.swing.views.file.explorer.FileExplorerViewIdentifier>\n    <viewEntityName>My Folder</viewEntityName>\n  </com.sgu.mazeexplorer.swing.views.file.explorer.FileExplorerViewIdentifier>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>true</boolean>\n  <string>Title</string>\n  <int>0</int>\n  <string>My Folder</string>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n<!-- View definition : END -->\n\n  <int>1</int>\n  <int>0</int>\n  <int>2</int>\n  <int>2</int>\n  <int>-1</int>\n  <boolean>false</boolean>\n  <int>1</int>\n  <int>1</int>\n  <int>2</int>\n  <int>0</int>\n  <boolean>true</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <boolean>true</boolean>\n  <boolean>true</boolean>\n  <float>0.5</float>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <string>Tab Window Properties</string>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <string>Tabbed Panel Properties</string>\n  <boolean>true</boolean>\n  <string>Tab Area Orientation</string>\n  <int>0</int>\n  <net.infonode.util.Direction serialization=\"custom\">\n    <net.infonode.util.Enum>\n      <short>0</short>\n    </net.infonode.util.Enum>\n  </net.infonode.util.Direction>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>true</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>0</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n</object-stream>','sguisse','PRIVATE','sguisse','2016-09-19 18:10:52','sguisse','2016-09-21 18:05:47'),('4',NULL,'sguisse','',NULL,NULL,NULL,'Outils','','TOOLS','current-user, moi, perso, outils','TOOLS','rgb(0, 0, 0)','\0','\0','\0','\0','/icons/toolbox.png','',NULL,'sguisse','PRIVATE','sguisse','2016-09-19 18:10:52',NULL,NULL),('5',NULL,'4','',NULL,NULL,NULL,'Outils-Web','','TOOLS','current-user, moi, perso, outils, web','TOOLS','rgb(0, 0, 0)','\0','\0','\0','\0','/icons/tools-web.png','',NULL,'sguisse','PRIVATE','sguisse','2016-09-19 18:10:52',NULL,NULL),('6',NULL,'1','',NULL,NULL,NULL,'perspective.tree.node.documentation',NULL,'Default',NULL,'DOCUMENTATION','rgb(0,0,0)','\0','\0','\0','\0','/icons/books.png','',NULL,'sguisse','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('7',NULL,'6','',NULL,NULL,NULL,'Java',NULL,'Default',NULL,'DOCUMENTATION','rgb(0,0,0)','\0','\0','\0','\0','/icons/java.png','',NULL,'sguisse','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('8',NULL,'6','',NULL,NULL,NULL,'Javascript',NULL,'Default',NULL,'DOCUMENTATION','rgb(0,0,0)','\0','\0','\0','\0','/icons/javascript.png','',NULL,'sguisse','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('9',NULL,'6','',NULL,NULL,NULL,'CSS',NULL,'Default',NULL,'DOCUMENTATION','rgb(0,0,0)','\0','\0','\0','\0','/icons/css.png','',NULL,'sguisse','PUBLIC','sguisse','2016-09-19 18:10:52',NULL,NULL),('sguisse',NULL,'1','',NULL,NULL,NULL,'sguisse','perspective.tree.node.user.tooltip','USER','current-user, moi','USER','rgb(0, 0, 255)','','\0','','\0','/icons/user.png','','<object-stream>\n  <int>4</int>\n  <boolean>true</boolean>\n  <int>1</int>\n  <int>484</int>\n<!-- View definition : BEGIN -->\n  <com.sgu.mazeexplorer.swing.views.web.WebViewIdentifier>\n    <viewEntityName>My Web site</viewEntityName>\n  </com.sgu.mazeexplorer.swing.views.web.WebViewIdentifier>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>true</boolean>\n  <string>Title</string>\n  <int>0</int>\n  <string>My Web site</string>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n<!-- View definition : END -->\n\n  <int>1</int>\n  <int>1</int>\n  <int>1</int>\n  <int>2</int>\n  <int>0</int>\n  <boolean>true</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <boolean>true</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <string>Tab Window Properties</string>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <string>Tabbed Panel Properties</string>\n  <boolean>true</boolean>\n  <string>Tab Area Orientation</string>\n  <int>0</int>\n  <net.infonode.util.Direction serialization=\"custom\">\n    <net.infonode.util.Enum>\n      <short>0</short>\n    </net.infonode.util.Enum>\n  </net.infonode.util.Direction>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <boolean>true</boolean>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>true</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>200</int>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>1</int>\n  <boolean>false</boolean>\n  <boolean>false</boolean>\n  <int>-1</int>\n  <int>0</int>\n  <int>0</int>\n  <int>4</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>-1</int>\n  <int>0</int>\n  <int>-1</int>\n  <int>-1</int>\n</object-stream>','sguisse','PRIVATE','sguisse','2016-09-19 18:10:52','sguisse','2016-09-26 11:56:56');

/* Function  structure for function  `nextval` */

/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
DELIMITER $$

/*!50003 CREATE FUNCTION `nextval`(`seq_name` VARCHAR(100)) RETURNS bigint(20)
BEGIN
    DECLARE cur_val BIGINT(20);
 
    SELECT
        cur_value INTO cur_val
    FROM
        sequence
    WHERE
        NAME = seq_name
    ;
 
    IF cur_val IS NOT NULL THEN
        UPDATE
            sequence
        SET
            cur_value = IF (
                (cur_value + increment) > MAX_VALUE,
                IF (
                    cycle = TRUE,
                    min_value,
                    NULL
                ),
                cur_value + increment
            )
        WHERE
            NAME = seq_name
        ;
    END IF;
 
    RETURN cur_val;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
