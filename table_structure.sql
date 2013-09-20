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

CREATE TABLE belief (
    student integer NOT NULL,
    religion integer NOT NULL
);


ALTER TABLE public.belief OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 16447)
-- Name: hobby; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hobby (
    id integer NOT NULL,
    type text
);


ALTER TABLE public.hobby OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16445)
-- Name: hobby_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

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

CREATE TABLE interest (
    student integer NOT NULL,
    hobby integer NOT NULL
);


ALTER TABLE public.interest OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16421)
-- Name: relationship; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE relationship (
    student1 integer NOT NULL,
    student2 integer NOT NULL,
    type text
);


ALTER TABLE public.relationship OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16436)
-- Name: religion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE religion (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.religion OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16434)
-- Name: religion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

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
-- TOC entry 1977 (class 0 OID 16429)
-- Dependencies: 173
-- Data for Name: belief; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY belief (student, religion) FROM stdin;
\.


--
-- TOC entry 1981 (class 0 OID 16447)
-- Dependencies: 177
-- Data for Name: hobby; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hobby (id, type) FROM stdin;
\.


--
-- TOC entry 1994 (class 0 OID 0)
-- Dependencies: 176
-- Name: hobby_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hobby_id_seq', 1, false);


--
-- TOC entry 1982 (class 0 OID 16456)
-- Dependencies: 178
-- Data for Name: interest; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY interest (student, hobby) FROM stdin;
\.


--
-- TOC entry 1976 (class 0 OID 16421)
-- Dependencies: 172
-- Data for Name: relationship; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY relationship (student1, student2, type) FROM stdin;
\.


--
-- TOC entry 1979 (class 0 OID 16436)
-- Dependencies: 175
-- Data for Name: religion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY religion (id, name) FROM stdin;
\.


--
-- TOC entry 1995 (class 0 OID 0)
-- Dependencies: 174
-- Name: religion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('religion_id_seq', 1, false);


--
-- TOC entry 1975 (class 0 OID 16399)
-- Dependencies: 171
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY student (id, name, address, email, password, birthdate, privacy, salt, isadmin) FROM stdin;
\.


--
-- TOC entry 1996 (class 0 OID 0)
-- Dependencies: 170
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('student_id_seq', 1, false);


--
-- TOC entry 1860 (class 2606 OID 16433)
-- Name: beleif_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY belief
    ADD CONSTRAINT beleif_primary PRIMARY KEY (student, religion);


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
    ADD CONSTRAINT interest_primary PRIMARY KEY (student, hobby);


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
    ADD CONSTRAINT relationship_primary PRIMARY KEY (student1, student2);


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

