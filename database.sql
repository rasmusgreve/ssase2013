--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.9
-- Dumped by pg_dump version 9.1.9
-- Started on 2013-09-19 22:39:08

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 162 (class 3079 OID 11639)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1857 (class 0 OID 0)
-- Dependencies: 162
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 161 (class 1259 OID 16395)
-- Dependencies: 5
-- Name: MyUser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "MyUser" (
    id integer NOT NULL,
    name text,
    pass text
);


ALTER TABLE public."MyUser" OWNER TO postgres;

--
-- TOC entry 1849 (class 0 OID 16395)
-- Dependencies: 161 1850
-- Data for Name: MyUser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "MyUser" (id, name, pass) FROM stdin;
1	one	pass
\.


--
-- TOC entry 1848 (class 2606 OID 16399)
-- Dependencies: 161 161 1851
-- Name: ID_PRIMARY; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "MyUser"
    ADD CONSTRAINT "ID_PRIMARY" PRIMARY KEY (id);


--
-- TOC entry 1856 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-09-19 22:39:08

--
-- PostgreSQL database dump complete
--

