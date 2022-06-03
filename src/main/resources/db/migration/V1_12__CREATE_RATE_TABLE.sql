CREATE TABLE IF NOT EXISTS public.rate (
     rate_id varchar(32),
     created_at timestamp without time zone,
     value DOUBLE PRECISION,
     currency varchar(32),
     CONSTRAINT rate_pkey PRIMARY KEY (rate_id),
     CONSTRAINT rate_currency_fk
         FOREIGN KEY (currency)
             REFERENCES public.currency(currency_id)
             ON DELETE NO ACTION
             ON UPDATE CASCADE

  );