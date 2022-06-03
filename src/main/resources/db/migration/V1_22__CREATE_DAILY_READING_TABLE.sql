CREATE TABLE IF NOT EXISTS public.daily_reading (
      daily_reading_id varchar(32) NOT NULL,
      created_at timestamp without time zone,
      dispensed_volume bigint,
      daily_user varchar(64),
      location_id VARCHAR(32),
      pump_id varchar(32),
      tank_id varchar(32),
      nozzle_id varchar(32),
      CONSTRAINT daily_reading_pkey PRIMARY KEY (daily_reading_id)
);