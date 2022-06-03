CREATE TABLE public.dip_sheet(
    tank_id VARCHAR(32) NOT NULL,
    dip_sheet_id VARCHAR(32) NOT NULL,
    metric_measurement INTEGER,
    volume BIGINT,
    CONSTRAINT dip_sheet_pkey PRIMARY KEY (tank_id, dip_sheet_id),
    CONSTRAINT tank_dip_sheet_fk
        FOREIGN KEY (tank_id)
            REFERENCES public.tank(tank_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
DROP TABLE public.nozzle;