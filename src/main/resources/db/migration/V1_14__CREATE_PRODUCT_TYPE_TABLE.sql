CREATE TABLE IF NOT EXISTS public.product_type (
   type_id varchar(32),
   descriptor_type varchar(16) ,
   CONSTRAINT product_type_pkey PRIMARY KEY (type_id)
);