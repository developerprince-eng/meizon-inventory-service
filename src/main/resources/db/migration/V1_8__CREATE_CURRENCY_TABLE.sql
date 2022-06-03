CREATE TABLE IF NOT EXISTS public.currency (
      currency_id varchar(32),
      name varchar(8) ,
      CONSTRAINT currency_pkey PRIMARY KEY (currency_id)
);