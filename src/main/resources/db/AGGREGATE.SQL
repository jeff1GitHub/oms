CREATE AGGREGATE "public"."array_accum" ( anyelement ) ( 
	SFUNC = "pg_catalog"."array_append",
	STYPE = "pg_catalog"."anyarray",
	INITCOND = "{}"
);

ALTER AGGREGATE "public"."array_accum"(anyelement) OWNER TO "postgres";