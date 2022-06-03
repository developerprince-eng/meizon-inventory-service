CREATE TABLE IF NOT EXISTS public.location_type (
   type_id varchar(32),
   descriptor_type varchar(255) ,
   CONSTRAINT location_type_pkey PRIMARY KEY (type_id)
);