CREATE TABLE IF NOT EXISTS public.payment_type (
   type_id varchar(32),
   descriptor_type varchar(8) ,
   CONSTRAINT payment_type_id_pkey PRIMARY KEY (type_id)
);