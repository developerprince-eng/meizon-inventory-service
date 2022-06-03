CREATE TABLE public.pump_readings(
    pump_id VARCHAR(32) NOT NULL,
    user_id VARCHAR(32) NOT NULL,
    pump_reading_id VARCHAR(32),
    open_volume INTEGER,
    close_volume INTEGER,
    created_date timestamp without time zone,
    CONSTRAINT pump_daily_reading_pkey PRIMARY KEY (pump_id, user_id, pump_reading_id),
    CONSTRAINT pump_daily_reading_pump_fk
        FOREIGN KEY (pump_id)
            REFERENCES public.pump(pump_id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
);