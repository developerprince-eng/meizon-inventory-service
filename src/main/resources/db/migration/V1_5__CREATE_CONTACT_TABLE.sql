CREATE TABLE IF NOT EXISTS public.contact (
     contact_id varchar(255) NOT NULL,
     type varchar(64) ,
     detail  varchar(255),
     CONSTRAINT contact_pkey PRIMARY KEY (contact_id)
);