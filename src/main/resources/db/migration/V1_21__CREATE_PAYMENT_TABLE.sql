CREATE TABLE IF NOT EXISTS public.payment (
  payment_id varchar(32),
  payment_date timestamp without time zone,
  amount DOUBLE PRECISION,
  payment_histories varchar(32),
  CONSTRAINT payment_pkey PRIMARY KEY (payment_id),
  CONSTRAINT payment_history_payment_fk
      FOREIGN KEY (payment_histories)
          REFERENCES public.payment_history(payment_history_id)
          ON DELETE CASCADE
          ON UPDATE NO ACTION
);