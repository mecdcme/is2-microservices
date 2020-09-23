-- is2.is2_workflow_status definition

CREATE TABLE `is2_workflow_status` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `work_session_id` bigint unsigned NOT NULL,
  `business_function_id` bigint unsigned NOT NULL,
  `status` int unsigned NOT NULL,
  `creationDate` timestamp NOT NULL,
  `creatorUser` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `modifyDate` timestamp NULL DEFAULT NULL,
  `modifierUser` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- is2.is2_workflow_status_history definition

CREATE TABLE `is2_workflow_status_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `workflow_status_id` bigint NOT NULL,
  `work_session_id` bigint NOT NULL,
  `business_function_id` bigint NOT NULL,
  `status` int NOT NULL,
  `creationDate` timestamp NOT NULL,
  `creatorUser` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `modifyDate` timestamp NULL DEFAULT NULL,
  `modifierUser` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
