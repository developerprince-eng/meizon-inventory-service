CREATE TABLE IF NOT EXISTS public.contact_type (
   type_id varchar(32),
   descriptor_type varchar(255) ,
   CONSTRAINT contact_type_pkey PRIMARY KEY (type_id)
);