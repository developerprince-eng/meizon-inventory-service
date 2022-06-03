CREATE TABLE IF NOT EXISTS public.category (
       category_id varchar(32),
       name varchar(196),
       description text,
       CONSTRAINT category_pkey PRIMARY KEY (category_id)
);