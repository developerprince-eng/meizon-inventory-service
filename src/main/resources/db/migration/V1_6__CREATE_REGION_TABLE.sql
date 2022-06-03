CREATE TABLE IF NOT EXISTS public.region (
   region_id varchar(16) NOT NULL,
   province varchar(255) ,
   description text,
   CONSTRAINT region_pkey PRIMARY KEY (region_id)
);