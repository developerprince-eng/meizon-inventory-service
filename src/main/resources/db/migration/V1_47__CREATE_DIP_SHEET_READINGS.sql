CREATE TABLE public.dip_sheet_daily_reading(
       tank_id VARCHAR(32) NOT NULL,
       user_id VARCHAR(32) NOT NULL,
       daily_reading_id VARCHAR(32) NOT NULL,
       open_volume INTEGER,
       close_volume INTEGER,
       created_date timestamp without time zone,
       CONSTRAINT dip_sheet_daily_reading_pkey PRIMARY KEY (tank_id, user_id, daily_reading_id),
       CONSTRAINT dip_sheet_daily_reading_tank_fk
           FOREIGN KEY (tank_id)
               REFERENCES public.tank(tank_id)
               ON DELETE NO ACTION
               ON UPDATE CASCADE
);