CREATE TABLE IF NOT EXISTS public.pump_type (
   type_id varchar(32),
   descriptor_type varchar(255) NOT NULL,
   CONSTRAINT pump_type_pkey PRIMARY KEY (type_id)
);