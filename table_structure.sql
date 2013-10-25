--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.0
-- Dumped by pg_dump version 9.3.0
-- Started on 2013-09-20 14:08:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 179 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1990 (class 0 OID 0)
-- Dependencies: 179
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


DROP TABLE interest;
CREATE TABLE interest (
    id integer NOT NULL UNIQUE,
    student integer NOT NULL,
    hobby integer NOT NULL
);

ALTER TABLE public.interest OWNER TO postgres;

CREATE SEQUENCE interest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.interest_id_seq OWNER TO postgres;
ALTER SEQUENCE interest_id_seq OWNED BY interest.id;

DROP TABLE hobby;
CREATE TABLE hobby (
    id integer NOT NULL UNIQUE,
    type text
);


ALTER TABLE public.hobby OWNER TO postgres;


CREATE SEQUENCE hobby_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hobby_id_seq OWNER TO postgres;

ALTER SEQUENCE hobby_id_seq OWNED BY hobby.id;


DROP TABLE relationship;
CREATE TABLE relationship (
	id integer NOT NULL UNIQUE,
    student1 integer NOT NULL,
    student2 integer NOT NULL,
    type text
);
ALTER TABLE public.relationship OWNER TO postgres;
CREATE SEQUENCE relationship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.relationship_id_seq OWNER TO postgres;
ALTER SEQUENCE relationship_id_seq OWNED BY relationship.id;



--
-- TOC entry 171 (class 1259 OID 16399)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--
DROP TABLE student;
CREATE TABLE student (
    id integer NOT NULL UNIQUE,
    name text,
    address text,
    email text,
    password text,
    birthdate date,
    privacy integer,
    salt text,
    isadmin boolean DEFAULT false
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16397)
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
DROP SEQUENCE student_id_seq;
CREATE SEQUENCE student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_id_seq OWNER TO postgres;

--
-- TOC entry 1993 (class 0 OID 0)
-- Dependencies: 170
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE student_id_seq OWNED BY student.id;

ALTER TABLE relationship ADD CONSTRAINT student1_id FOREIGN KEY (student1) REFERENCES student (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE relationship ADD CONSTRAINT student2_id FOREIGN KEY (student2) REFERENCES student (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE interest ADD CONSTRAINT student3_id FOREIGN KEY (student) REFERENCES student (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE interest ADD CONSTRAINT hobby1_id FOREIGN KEY (hobby) REFERENCES hobby (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--
-- TOC entry 1854 (class 2604 OID 16450)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

--ALTER TABLE ONLY hobby ALTER COLUMN id SET DEFAULT nextval('hobby_id_seq'::regclass);


--
-- TOC entry 1853 (class 2604 OID 16439)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

--ALTER TABLE ONLY religion ALTER COLUMN id SET DEFAULT nextval('religion_id_seq'::regclass);


--
-- TOC entry 1864 (class 2606 OID 16455)
-- Name: hobby_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hobby
    ADD CONSTRAINT hobby_primary PRIMARY KEY (id);


--
-- TOC entry 1866 (class 2606 OID 16460)
-- Name: interest_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY interest
    ADD CONSTRAINT interest_primary PRIMARY KEY (id);


--
-- TOC entry 1856 (class 2606 OID 16408)
-- Name: primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY student
    ADD CONSTRAINT "primary" PRIMARY KEY (id);


--
-- TOC entry 1858 (class 2606 OID 16428)
-- Name: relationship_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY relationship
    ADD CONSTRAINT relationship_primary PRIMARY KEY (id);


--
-- TOC entry 1989 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT SELECT, INSERT, UPDATE, DELETE ON SCHEMA public TO tomcat;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO tomcat;

-- Completed on 2013-09-20 14:08:56

--
-- PostgreSQL database dump complete
--

