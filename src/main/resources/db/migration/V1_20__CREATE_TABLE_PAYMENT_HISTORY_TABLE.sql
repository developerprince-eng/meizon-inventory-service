CREATE TABLE IF NOT EXISTS public.payment_history (
  payment_history_id varchar(32),
  payment_time timestamp without time zone,
  amount DOUBLE PRECISION,
  payment_type varchar(32),
  currency varchar(32),
  customer varchar(32),
  CONSTRAINT payment_history_pkey PRIMARY KEY (payment_history_id),
  CONSTRAINT payment_history_currency_fk
      FOREIGN KEY (currency)
          REFERENCES public.currency(currency_id)
          ON DELETE NO ACTION
          ON UPDATE CASCADE,
  CONSTRAINT payment_history_payment_type_fk
      FOREIGN KEY (payment_type)
          REFERENCES public.payment_type(type_id)
          ON DELETE NO ACTION
          ON UPDATE CASCADE,
  CONSTRAINT payment_history_customer_fk
      FOREIGN KEY (customer)
          REFERENCES public.customer(customer_id)
          ON DELETE NO ACTION
          ON UPDATE CASCADE
);