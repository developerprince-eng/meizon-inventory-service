CREATE TABLE public.pump_side(
    pump_id VARCHAR(32),
    pump_side_id VARCHAR(32),
    CONSTRAINT pump_pump_side_fk
        FOREIGN KEY (pump_id)
            REFERENCES public.pump(pump_id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
);