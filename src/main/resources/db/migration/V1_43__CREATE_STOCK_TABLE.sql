CREATE TABLE IF NOT EXISTS public.stock (
       stock_id varchar(32) NOT NULL,
       delivery_date timestamp without time zone,
       delivered_volume DOUBLE PRECISION,
       aggregated_volume DOUBLE PRECISION,
       product varchar(16),
       receiver VARCHAR(16),
       location VARCHAR(16),
       stock_history varchar(32),
       supplier varchar(32),
       CONSTRAINT stock_pkey PRIMARY KEY (stock_id),
       CONSTRAINT stock_product_fk
           FOREIGN KEY (product)
               REFERENCES public.product(product_sku)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION,
       CONSTRAINT stock_history_fk
           FOREIGN KEY (stock_history)
               REFERENCES public.stock_history(stock_history_id)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION,
       CONSTRAINT stock_location_fk
           FOREIGN KEY (location)
               REFERENCES public.location(location_id)
               ON DELETE CASCADE
               ON UPDATE NO ACTION,
       CONSTRAINT stock_supplier_fk
           FOREIGN KEY (supplier)
               REFERENCES public.supplier(supplier_id)
               ON DELETE CASCADE
               ON UPDATE CASCADE
);