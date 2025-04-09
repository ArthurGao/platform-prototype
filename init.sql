-- init.sql
DO
$$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_user
      WHERE usename = 'demo'
   ) THEN
      CREATE USER demo WITH PASSWORD 'demo';
END IF;
END
$$;

DO
$$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_database
      WHERE datname = 'demodb1'
   ) THEN
      CREATE DATABASE demodb1 WITH OWNER = demo ENCODING = 'UTF8'
         LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8' TEMPLATE = template0;
END IF;
END
$$;