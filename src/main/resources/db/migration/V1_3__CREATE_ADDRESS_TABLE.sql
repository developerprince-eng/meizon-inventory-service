CREATE TABLE IF NOT EXISTS public.address (
   address_id varchar(32),
   street varchar(255) ,
   unit_number varchar(16),
   CONSTRAINT address_pkey PRIMARY KEY (address_id)
);