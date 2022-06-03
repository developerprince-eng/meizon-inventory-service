CREATE TABLE IF NOT EXISTS public.pump (
   pump_id varchar(32),
   description text,
   tank_id varchar(32),
   type varchar(32),
   CONSTRAINT pump_pkey PRIMARY KEY (pump_id)
);