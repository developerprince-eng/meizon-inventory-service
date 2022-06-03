CREATE TABLE IF NOT EXISTS public.user_permission (
   permission_id varchar(32),
   name varchar(255) NOT NULL,
   description  text,
   CONSTRAINT permission_pkey PRIMARY KEY (permission_id)
);