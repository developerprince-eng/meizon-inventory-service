CREATE TABLE IF NOT EXISTS public.account_type (
   type_id varchar(32),
   descriptor_type varchar(255) ,
   CONSTRAINT account_type_pkey PRIMARY KEY (type_id)
);