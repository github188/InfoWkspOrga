/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.23 : Database - info_wrksp_orga
*********************************************************************
*/



/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE  IF NOT EXISTS `info_wrksp_orga` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

USE `info_wrksp_orga`;

/*Table structure for table `i18n` */

DROP TABLE IF EXISTS `i18n`;

CREATE TABLE `i18n` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `language` CHAR(2) DEFAULT 'fr' COMMENT 'language = ''fr'' by default',
  `value` VARCHAR(255) DEFAULT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_i18n_Biz_PK` (`name`,`language`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Manage Business data internationalization';

/*Data for the table `i18n` */

/*Table structure for table `lock` */

DROP TABLE IF EXISTS `lock`;

CREATE TABLE `lock` (
  `id` INT(11) NOT NULL,
  `entity_name` VARCHAR(100) NOT NULL COMMENT 'The java entity name without Package = Table name',
  `entity_id` INT(11) NOT NULL COMMENT 'Id of the locked entity = Table Id',
  `max_duration` INT(11) NOT NULL DEFAULT '10' COMMENT 'in second. -1 = infinite',
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_lock_Biz_PK` (`entity_name`,`entity_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Manage Table entity lock';

/*Data for the table `lock` */


/*Table structure for table `preference` */

DROP TABLE IF EXISTS `preference`;

CREATE TABLE `preference` (
  `id` INT(11) NOT NULL,
  `parent_id` INT(11) COMMENT 'Le node parent de cette preference, permet de construire un arbre de propriété (cf preferences Eclipse)',
  `name` VARCHAR(50) NOT NULL COMMENT 'La clé de la preference',
  
  `description` VARCHAR(250),
  `category` VARCHAR(50) COMMENT 'On peut voir la ''category'' comme un  groupe. Regroupement d''une même famille de propriétés (ex: Adresse; Personne; ...) au sein d''un même node',
  
  `type` ENUM('TREE_NODE','OBJECT','STRING','DESCRIPTION','PASSWORD','BOOLEAN','INTEGER','DOUBLE','DATE','TIME','DATE_TIME','LIST','COLOR') DEFAULT 'STRING' NOT NULL COMMENT 'Le type de donnée (int, string, object, ...) de la preference',
  `value` LONGTEXT  COMMENT 'La valeur de la preference',
  `constraints` VARCHAR(500) COMMENT 'Liste des constraints (cf régles Drools) separated by ;',
  
  `updatable` BIT(1) NOT NULL DEFAULT b'1' COMMENT 'Flag qui indique si on peut mettre à jour la preference' ,
  `enabled` BIT(1) NOT NULL DEFAULT b'1'COMMENT 'Flag qui indique si il faut prendre en compte la preference',
  `order` INT(11) NOT NULL COMMENT 'Index d''affichage de la préférence',
  
  `localization` ENUM('DATABASE','LOCAL_PROPERTIES_FILE') COMMENT 'Indique où est définie la préférence: en base ou dans un fichier properties',
 
  `owner` VARCHAR(45) NOT NULL COMMENT 'Généralement identique au createdBy. Correspond à la personne habilitée a gérer cette organisation de workspaces',
  `partage` ENUM('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PUBLIC' COMMENT 'Private correspond à une visibilité au créateur (propriétaire)',
 
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_preference_Biz_PK` (`category`,`name`),
  KEY `FK_parent_preference_IDX` (`parent_id`),
  CONSTRAINT `FK_SELF_PARENT` FOREIGN KEY (`parent_id`) REFERENCES `preference` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Manage application preferences';

/*Data for the table `preference` */

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` INT(11) NOT NULL,
  `parent_id` INT(11) COMMENT 'Le node parent de cette projet, permet de construire un arbre de projet ayant des liens entre eux',
  
  `name` VARCHAR(50) NOT NULL COMMENT 'Correspond au code projet',
  `description` VARCHAR(255) DEFAULT NULL COMMENT 'Décrit l''objectif visé par la création de ce projet (suivi d''une affaire, d''un projet informatique, suivi de ses comptes personnels, Faciliter l''accés aux outils,...)',
  `category` VARCHAR(45) NOT NULL COMMENT 'Groupe d''appartenance de ce projet (ex : Projet pour BNPP, Projet pour SNCF, Projet personnel) ==> BNPP; SNCF; Personnel',
  `enabled` BIT(1) NOT NULL DEFAULT b'1',
  
  `owner` VARCHAR(45) NOT NULL COMMENT 'Généralement identique au createdBy. Correspond à la personne habilitée a gérer cette organisation de workspaces',
  `partage` ENUM('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PRIVATE' COMMENT 'Private correspond à une visibilité au créateur (propriétaire) du workspace',
 
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_project_Biz_PK` (`name`,`category`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT='Manage workspace organization. It specify a special node to create workspaces for it';

/*Data for the table `project` */

INSERT  INTO `project`(`id`,`name`,`description`,`category`,`enabled`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) 
        VALUES (1,'sguisse','My personnal project','sguisse',1,'sguisse','2016-07-11 14:03:27',NULL,NULL);

INSERT INTO `info_wrksp_orga`.`project` (
  `id`,
  `name`,
  `description`,
  `category`,
  `enabled`,
  `created_by`,
  `created_date`,
  `last_modified_by`,
  `last_modified_date`
) 
VALUES
  (
    0,
    'Default',
    'Default Project',
    'Default',
     1,
    'sguisse',
    CURRENT_TIMESTAMP,
    NULL,
    NULL
  ) ;

/*Table structure for table `project_configuration` */

DROP TABLE IF EXISTS `project_configuration`;

CREATE TABLE `project_configuration` (
  `id` INT(11) NOT NULL,
  `project_id` INT(11) NOT NULL,
  `category` VARCHAR(45) NOT NULL COMMENT 'Regroupement d''une même famille de propriétés (ex: Adresse; Personne; ...)',
  `name` VARCHAR(50) NOT NULL COMMENT 'La cle de la preference',
  `type` VARCHAR(50) NOT NULL COMMENT 'Le type de donnée (int, string, object, ...) de la preference',
  `value` LONGTEXT NOT NULL COMMENT 'La valeur de la preference',
  `constraints` VARCHAR(500) DEFAULT NULL COMMENT 'Liste des constraints (cf régle Drools) separated by ;',
  `updatable` BIT(1) NOT NULL DEFAULT b'1',
  `mandatory` BIT(1) NOT NULL DEFAULT b'0',
  `order` INT(11) NOT NULL,
  `description` VARCHAR(250) DEFAULT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_project_configuration_Biz_PK` (`project_id`,`category`,`name`),
  KEY `FK_project_configuration_IDX` (`project_id`),
  CONSTRAINT `FK_project_configuration` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Permet de définir et afficher les propriétés (informations) principales du projet dans le workspace attaché directement au projet';

/*Data for the table `project_configuration` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` INT(11) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `firstname` VARCHAR(30) NOT NULL COMMENT 'Prénom',
  `lastname` VARCHAR(30) NOT NULL COMMENT 'Nom de famille',
  `password` VARCHAR(30) NOT NULL,
  `email_1` VARCHAR(45) NOT NULL COMMENT 'Mail principal de l''utilisateur',
  `email_2` VARCHAR(45) DEFAULT NULL COMMENT 'Mail où on peux joindre le collaborateur chez un client par exemple',
  `enabled` BIT(1) NOT NULL DEFAULT b'1',
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_user_Biz_PK` (`login`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT='Contient l''ensemble des utilisateurs pouvant accéder à l''application';

/*Data for the table `user` */

INSERT INTO `info_wrksp_orga`.`user` (
  `id`,
  `login`,
  `firstname`,
  `lastname`,
  `password`,
  `email_1`,
  `email_2`,
  `enabled`,
  `created_by`,
  `created_date`,
  `last_modified_by`,
  `last_modified_date`
) 
VALUES
  (
    '0',
    'admin',
    'Sébastien',
    'Guisse',
    '211276',
    'sebastien.guisse@gfi.fr',
    'sebguisse@gmail.com',
    1,
    'sguisse',
    CURRENT_TIMESTAMP,
    NULL,
    NULL
  ) ;
  
  INSERT INTO `info_wrksp_orga`.`user` (
  `id`,
  `login`,
  `firstname`,
  `lastname`,
  `password`,
  `email_1`,
  `email_2`,
  `enabled`,
  `created_by`,
  `created_date`,
  `last_modified_by`,
  `last_modified_date`
) 
VALUES
  (
    '1',
    'sguisse',
    'Sébastien',
    'Guisse',
    '211276',
    'sebastien.guisse@gfi.fr',
    'sebguisse@gmail.com',
    1,
    'sguisse',
    CURRENT_TIMESTAMP,
    NULL,
    NULL
  ) ;


/*Table structure for table `perspective` */

DROP TABLE IF EXISTS `perspective`;

CREATE TABLE `perspective` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL COMMENT 'code/nom permettant d''identifier ce modéle',
  `icon` VARCHAR(255) NULL COMMENT 'icone permettant d''identifier ce modéle',
  `description` VARCHAR(255) DEFAULT NULL,
  
  `owner` VARCHAR(45) NOT NULL COMMENT 'Généralement identique au createdBy. Correspond à la personne habilitée a gérer cette organisation de workspaces',
  `partage` ENUM('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PUBLIC' COMMENT 'Private correspond à une visibilité au créateur (propriétaire) ',
  
  `enabled` BIT(1) NOT NULL DEFAULT b'1',
  `order` INT(11) NOT NULL,
  
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_perspective_Biz_PK` (`name`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT='Cette table permet de définir via un fichier XML la structure de base des workspaces.';

/*Data for the table `perspective` */


/*Table structure for table `workspace` */

DROP TABLE IF EXISTS `workspace`;

CREATE TABLE `workspace` (
  `id` VARCHAR(11) NOT NULL COMMENT 'Le workspace est de type varchar pour permettre la creation d''un id avec le login utilisateur',
  `master_id` VARCHAR(11) COMMENT 'Indique que le LAYOUT à utiliser est celui de ce workspace id',
  `parent_id` VARCHAR(11) COMMENT 'Le workspace parent de ce workspace (= rattachement brut)',
  `children_wrksp_creation_enabled` BIT(1) NOT NULL DEFAULT b'1' COMMENT 'Indique si ce workspace peut avoir des enfants',
  
  `project_id` INT(11) COMMENT 'L''id de projet associé à ce workspace',
  `base_folder` VARCHAR(300) DEFAULT NULL COMMENT 'contient le repertoire de base pour les vues de type FileExplorer, utile dans le cadre d''1 workspace projet',
  `customer` VARCHAR(100) DEFAULT NULL COMMENT 'le nom du client auquel se rapporte ce workspace, utile dans le cadre d'' workspace projet',
  
  `name` VARCHAR(100) NOT NULL COMMENT 'contient un code/nom permettant de faciliter la recherche d''un workspace',
  `description` VARCHAR(250) DEFAULT NULL,
  `category` VARCHAR(50) NULL DEFAULT 'Default' COMMENT 'code de regroupement d''une même famille de workspaces',
  `tags` VARCHAR(500) NULL COMMENT 'Permet d''ajouter des étiquettes (séparées par des '';'') au Workspace pour en faciliter la recherche',
  `type` ENUM('ROOT','CLASSEUR','HELP','USER','ENTREPRISE','PROJECT','TMA','TEMPLATE','GESTION') NOT NULL DEFAULT 'CLASSEUR',
  
  `color` VARCHAR(30) NOT NULL DEFAULT 'rgb(0,0,0)' COMMENT 'couleur HTML RGB: 255, 255, 255',
  `bold` BIT(1) NOT NULL DEFAULT b'0',
  `strike` BIT(1) NOT NULL DEFAULT b'0',
  `italic` BIT(1) NOT NULL DEFAULT b'0',
  `underline` BIT(1) NOT NULL DEFAULT b'0',
  `icon` VARCHAR(300) DEFAULT NULL,
  
  `enabled` BIT(1) NOT NULL DEFAULT b'1',
  `order` INT(11) NOT NULL,
  
  `layout` LONGTEXT COMMENT 'Contient le layout du workspace, utilisé pour le reconstruire à l''identique lors de la sauvegarde',
  
  `owner` VARCHAR(45) NOT NULL COMMENT 'Par défaut = au créateur du Workspace',
  `partage` ENUM('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PRIVATE' COMMENT 'Private correspond à une visibilité au créateur (propriétaire) du workspace',
  
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_workspace_Biz_PK` (`name`,`type`)
  
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Table de stockage des workspaces.';

ALTER TABLE `info_wrksp_orga`.`workspace`  
  ADD CONSTRAINT `FK_MASTER` FOREIGN KEY (`master_id`) REFERENCES `info_wrksp_orga`.`workspace`(`id`),
  ADD CONSTRAINT `FK_PARENT` FOREIGN KEY (`parent_id`) REFERENCES `info_wrksp_orga`.`workspace`(`id`),
  ADD CONSTRAINT `FK_PROJET` FOREIGN KEY (`project_id`) REFERENCES `info_wrksp_orga`.`project`(`id`);

/*Data for the table `workspace` */  

/*Table structure for table `perspective_workspaces` */

DROP TABLE IF EXISTS `perspective_workspaces`;
  
CREATE TABLE `perspective_workspaces` (
  `id` INT(11) NOT NULL,
  
  `perspective_id` INT(11) NOT NULL,
  `workspace_id` VARCHAR(11) NOT NULL,
  
  
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier',
  `last_modified_date` TIMESTAMP NULL DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier',
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY (`perspective_id`, `workspace_id`),
  CONSTRAINT `FK_Perspective` FOREIGN KEY (`perspective_id`) REFERENCES `perspective` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Workspace`   FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Manage Workspaces attached to Perspective';

/*Data for the table `perspective_workspaces` */


/*Table structure for table `view` */

DROP TABLE IF EXISTS `view`;

CREATE TABLE `view` (
  `id` INT(11) NOT NULL,
  `workspace_id` VARCHAR(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL COMMENT 'Code manuel saisi par l''utilisateur pour l''identification de la vue (regroupement paire Attribute/Value',
  `java_type` VARCHAR(300) NOT NULL COMMENT 'Identifies the View Type (Package with .class name)',
  `icon` VARCHAR(300) NULL COMMENT 'Contient l''icône de la vue, peut être un path classpath ou une URL',
  
  `description` VARCHAR(300) NULL ,
  `category` VARCHAR(50) NOT NULL DEFAULT 'Default' COMMENT 'code de regroupement des vues, pour avoir une organisation différente au sein des workspaces de la même société',
  `tags` VARCHAR(500) NULL COMMENT 'Permet d''ajouter des étiquettes (séparées par des '';'') à la vue pour en faciliter la recherche',
  
  `cmmi_practices` VARCHAR(500) NULL COMMENT 'CMMI relative practices separated by ";"',
  
  `owner` VARCHAR(45) NOT NULL COMMENT 'Par défaut = au créateur de la vue',
  `partage` ENUM('PRIVATE','PUBLIC','AUTHORIZATION') NOT NULL DEFAULT 'PUBLIC' COMMENT 'Private correspond à une visibilité au créateur (propriétaire) du workspace',
  
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier, MAJ par Annuler/Remplace',
  `last_modified_date` DATETIME NULL DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier, MAJ par Annuler/Remplace',
  PRIMARY KEY (`id`),
  KEY `FK_m25k9x3con7ytjgtr2ifqfm9y` (`workspace_id`,`name`), /* Name is unique for a workspace */
  CONSTRAINT `FK_Workspace_View` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Contient l''ensemble des vues des workspaces avec leur configuration pour pouvoir être reconstruite lors du chargement du workspace';

/*Data for the table `view` */


/*Table structure for table `view_attribute` */

DROP TABLE IF EXISTS `view_attribute`;

CREATE TABLE `view_attribute` (
  `id` INT(11) NOT NULL,
  `view_id` INT(11) NOT NULL COMMENT 'clé d''appartenance des cet attibut à une vue',
  
  `name` VARCHAR(45) NOT NULL COMMENT 'nom de cet attibut',
  `value` LONGTEXT NULL,
 
  `created_by` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(45) DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier, MAJ par Annuler/Remplace',
  `last_modified_date` DATETIME NULL DEFAULT NULL COMMENT 'Non utilisé. Présent pour raison de compatibilité avec le Fwk Métier, MAJ par Annuler/Remplace',
  PRIMARY KEY (`id`),
  KEY `FK_m25k9x3con7ytjgtr2ifqfm9y` (`view_id`),
  CONSTRAINT `FK_View` FOREIGN KEY (`view_id`) REFERENCES `view` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Contient l''ensemble des attributs des vues';

/*Data for the table `view_attribute` */


/*Table structure for table `sequence` */
/* http://www.microshell.com/database/mysql/emulating-nextval-function-to-get-sequence-in-mysql/ */

DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
    `name` VARCHAR(100) NOT NULL,
    `increment` INT(11) UNSIGNED NOT NULL DEFAULT 1,
    `min_value` INT(11) UNSIGNED NOT NULL DEFAULT 1,
    `max_value` BIGINT(20) UNSIGNED NOT NULL DEFAULT 18446744073709551615,
    `cur_value` BIGINT(20) UNSIGNED DEFAULT 1000,
    `cycle` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`name`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8 COMMENT='Permet de gérer les id de chacune des tables de l''application. La function définie ''nextval'' prend en param le nom d''une sequence et retourne le nouvel id pour la table associée à cette séquence';

/*Data for the table `sequence` */

INSERT  INTO `sequence`(`name`) VALUES 
('I18N_SEQ'),
('LOCK_SEQ'),

('PERSPECTIVE_SEQ'),
('WORKSPACE_SEQ'),
('PERSPECTIVE_WORKSPACES_SEQ'),
('VIEW_SEQ'),
('VIEW_ATTRIBUTE_SEQ'),

('PREFERENCE_SEQ'),
('PROJECT_SEQ'),
('PROJECT_CONFIGURATION_SEQ'),

('USER_SEQ');



SET FOREIGN_KEY_CHECKS = 1;

/* Function  structure for function  `nextval` */

/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
DELIMITER $$
 
CREATE FUNCTION `nextval` (`seq_name` VARCHAR(100))
RETURNS BIGINT(20) NOT DETERMINISTIC
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
END$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
