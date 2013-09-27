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

--
-- TOC entry 173 (class 1259 OID 16429)
-- Name: belief; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--
DROP TABLE belief;
CREATE TABLE belief (
    id integer NOT NULL,
    student integer NOT NULL,
    religion integer NOT NULL
);

ALTER TABLE public.belief OWNER TO postgres;

----DROP SEQUENCE belief_id_seq;
CREATE SEQUENCE belief_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public.belief_id_seq OWNER TO postgres;
ALTER SEQUENCE belief_id_seq OWNED BY belief.id;
--
-- TOC entry 177 (class 1259 OID 16447)
-- Name: hobby; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

DROP TABLE hobby;
CREATE TABLE hobby (
    id integer NOT NULL,
    type text
);


ALTER TABLE public.hobby OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16445)
-- Name: hobby_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
--DROP SEQUENCE hobby_id_seq;
CREATE SEQUENCE hobby_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hobby_id_seq OWNER TO postgres;

--
-- TOC entry 1991 (class 0 OID 0)
-- Dependencies: 176
-- Name: hobby_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hobby_id_seq OWNED BY hobby.id;


--
-- TOC entry 178 (class 1259 OID 16456)
-- Name: interest; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--
DROP TABLE interest;
CREATE TABLE interest (
    id integer NOT NULL,
    student integer NOT NULL,
    hobby integer NOT NULL
);

ALTER TABLE public.interest OWNER TO postgres;

--DROP SEQUENCE interest_id_seq;
CREATE SEQUENCE interest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.interest_id_seq OWNER TO postgres;
ALTER SEQUENCE interest_id_seq OWNED BY interest.id;

--
-- TOC entry 172 (class 1259 OID 16421)
-- Name: relationship; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

DROP TABLE relationship;
CREATE TABLE relationship (
	id integer NOT NULL,
    student1 integer NOT NULL,
    student2 integer NOT NULL,
    type text
);
ALTER TABLE public.relationship OWNER TO postgres;
--DROP SEQUENCE relationship_id_seq;
CREATE SEQUENCE relationship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.relationship_id_seq OWNER TO postgres;
ALTER SEQUENCE relationship_id_seq OWNED BY relationship.id;

--
-- TOC entry 175 (class 1259 OID 16436)
-- Name: religion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

DROP TABLE religion;
CREATE TABLE religion (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.religion OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16434)
-- Name: religion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
--DROP SEQUENCE religion_id_seq;
CREATE SEQUENCE religion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.religion_id_seq OWNER TO postgres;

--
-- TOC entry 1992 (class 0 OID 0)
-- Dependencies: 174
-- Name: religion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE religion_id_seq OWNED BY religion.id;


--
-- TOC entry 171 (class 1259 OID 16399)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--
DROP TABLE student;
CREATE TABLE student (
    id integer NOT NULL,
    name text,
    address text,
    email text,
    password text,
    birthdate date,
    privacy text,
    salt text,
    isadmin boolean DEFAULT false
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16397)
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
--DROP SEQUENCE student_id_seq;
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


--
-- TOC entry 1854 (class 2604 OID 16450)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hobby ALTER COLUMN id SET DEFAULT nextval('hobby_id_seq'::regclass);


--
-- TOC entry 1853 (class 2604 OID 16439)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY religion ALTER COLUMN id SET DEFAULT nextval('religion_id_seq'::regclass);


--
-- TOC entry 1851 (class 2604 OID 16402)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY student ALTER COLUMN id SET DEFAULT nextval('student_id_seq'::regclass);

--
-- TOC entry 1860 (class 2606 OID 16433)
-- Name: beleif_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY belief
    ADD CONSTRAINT beleif_primary PRIMARY KEY (id);


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
-- TOC entry 1862 (class 2606 OID 16444)
-- Name: religion_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY religion
    ADD CONSTRAINT religion_primary PRIMARY KEY (id);


--
-- TOC entry 1989 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-09-20 14:08:56

--
-- PostgreSQL database dump complete
--

