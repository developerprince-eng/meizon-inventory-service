CREATE TABLE IF NOT EXISTS public.account_history (
      account_history_id varchar(32),
      transaction_date timestamp without time zone,
      volume varchar(32),
      CONSTRAINT account_history_pkey PRIMARY KEY (account_history_id),
      CONSTRAINT account_history_volume_fk
          FOREIGN KEY (volume)
              REFERENCES public.volume(volume_id)
              ON DELETE CASCADE
              ON UPDATE NO ACTION
);