CREATE TABLE public.pump_side_type(
      type_id SERIAL NOT NULL,
      side VARCHAR(2),
      CONSTRAINT pump_side_type_pkey PRIMARY KEY (type_id)
);