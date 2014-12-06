/*
 * PostgreSQL
 */
CREATE TABLE users
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  modified timestamp without time zone,
  version bigint NOT NULL,
  accepted_terms boolean NOT NULL,
  address character varying(255),
  admin boolean,
  birthday timestamp without time zone,
  email character varying(255) NOT NULL,
  firstname character varying(255) NOT NULL,
  lastname character varying(255) NOT NULL,
  mob character varying(255),
  password character varying(255) NOT NULL,
  username character varying(255) NOT NULL,
  CONSTRAINT k_user_pkey PRIMARY KEY (id),
  CONSTRAINT k_user_email_key UNIQUE (email),
  CONSTRAINT k_user_username_key UNIQUE (username)
);

CREATE TABLE comments
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  modified timestamp without time zone,
  approved boolean NOT NULL,
  approved_by bigint,
  comment character varying(255) NOT NULL,
  name character varying(100),
  user_id bigint,
  CONSTRAINT k_comment_pkey PRIMARY KEY (id),
  CONSTRAINT fk_user_id_key FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_approved_by_key FOREIGN KEY (approved_by)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

CREATE TABLE news
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  modified timestamp without time zone,
  version bigint NOT NULL,
  description text,
  name character varying(100),
  active boolean,
  enddate timestamp without time zone,
  startdate timestamp without time zone,
  CONSTRAINT k_news_pkey PRIMARY KEY (id)
);
