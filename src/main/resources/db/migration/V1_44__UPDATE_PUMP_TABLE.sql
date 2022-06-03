DROP TABLE public.daily_reading;
ALTER TABLE  public.pump
ADD COLUMN location VARCHAR(32);
ALTER TABLE  public.pump
ADD CONSTRAINT location_pump_fk
    FOREIGN KEY (location)
    REFERENCES public.location(location_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
ALTER TABLE public.pump
ADD CONSTRAINT tank_pump_fk
    FOREIGN KEY (tank_id)
        REFERENCES public.tank(tank_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;