CREATE TABLE IF NOT EXISTS public.stock_history (
      stock_history_id varchar(32),
      stock_update_time timestamp without time zone,
      amount DOUBLE PRECISION,
      recorded_user varchar(32),
      CONSTRAINT stock_history_pkey PRIMARY KEY (stock_history_id)
);