--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: hobby; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hobby (
    id integer NOT NULL,
    type text
);


ALTER TABLE public.hobby OWNER TO postgres;

--
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
-- Name: hobby_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hobby_id_seq OWNED BY hobby.id;


--
-- Name: hug; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hug (
    id integer NOT NULL,
    student1 integer NOT NULL,
    student2 integer NOT NULL,
    expiration date,
    mutual boolean DEFAULT false
);


ALTER TABLE public.hug OWNER TO postgres;

--
-- Name: hug_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hug_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hug_id_seq OWNER TO postgres;

--
-- Name: hug_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hug_id_seq OWNED BY hug.id;


--
-- Name: interest; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE interest (
    id integer NOT NULL,
    student integer NOT NULL,
    hobby integer NOT NULL
);


ALTER TABLE public.interest OWNER TO postgres;

--
-- Name: interest_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE interest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.interest_id_seq OWNER TO postgres;

--
-- Name: interest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE interest_id_seq OWNED BY interest.id;


--
-- Name: relationship; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE relationship (
    id integer NOT NULL,
    student1 integer NOT NULL,
    student2 integer NOT NULL,
    type integer NOT NULL,
    approved boolean DEFAULT false
);


ALTER TABLE public.relationship OWNER TO postgres;

--
-- Name: relationship_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE relationship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.relationship_id_seq OWNER TO postgres;

--
-- Name: relationship_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE relationship_id_seq OWNED BY relationship.id;


--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE student (
    id integer NOT NULL,
    name text,
    surname text,
    handle text,
    address text,
    password text,
    salt text,
    isadmin boolean DEFAULT false
);


ALTER TABLE public.student OWNER TO postgres;

--
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
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE student_id_seq OWNED BY student.id;


--
-- Data for Name: hobby; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hobby (id, type) FROM stdin;
1	Fishing
2	Geocaching
3	Cooking
4	Fencing
5	Equestrianism
6	Cosplaying
7	Knitting
8	Pottery
9	Antiquing
\.


--
-- Name: hobby_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hobby_id_seq', 9, true);


--
-- Data for Name: hug; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hug (id, student1, student2, expiration, mutual) FROM stdin;
\.


--
-- Name: hug_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hug_id_seq', 1, false);


--
-- Data for Name: interest; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY interest (id, student, hobby) FROM stdin;
1	2	2
2	2	6
3	2	3
4	614	5
5	614	7
6	614	6
7	615	1
8	617	1
9	617	3
10	617	4
11	617	5
12	617	7
13	617	2
14	617	6
15	617	8
16	617	9
17	618	1
18	1	7
19	1	2
20	1	8
21	1	9
22	1	5
23	1	1
24	1	3
25	1	4
26	1	6
31	619	8
32	620	5
33	623	3
34	621	3
35	621	6
36	621	4
37	622	7
38	623	9
39	621	7
40	630	4
41	630	1
42	630	2
43	630	3
44	630	5
45	630	6
46	631	4
47	632	5
\.


--
-- Name: interest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('interest_id_seq', 47, true);


--
-- Data for Name: relationship; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY relationship (id, student1, student2, type, approved) FROM stdin;
1	620	619	0	t
2	614	621	0	t
3	621	622	0	t
4	621	623	0	t
5	623	622	0	t
7	614	622	0	t
8	621	624	0	f
9	624	622	0	t
10	625	624	0	f
11	625	622	0	t
12	625	614	0	t
6	614	623	0	t
13	621	625	0	f
14	630	2	0	f
15	630	44	0	f
16	631	2	0	f
17	631	616	0	f
18	621	631	0	f
19	631	626	0	f
20	631	618	0	f
21	622	3	0	f
22	622	151	0	f
23	621	2	0	f
24	621	3	0	f
25	621	43	0	f
26	621	44	0	f
27	621	45	0	f
28	621	46	0	f
29	621	47	0	f
30	621	48	0	f
31	621	49	0	f
32	621	50	0	f
33	621	51	0	f
34	621	52	0	f
35	621	53	0	f
36	621	54	0	f
37	621	55	0	f
38	621	151	0	f
39	621	152	0	f
40	621	153	0	f
41	621	154	0	f
42	621	155	0	f
43	621	156	0	f
44	621	157	0	f
45	621	158	0	f
46	621	159	0	f
47	621	160	0	f
48	621	214	0	f
49	621	232	0	f
50	621	233	0	f
51	621	234	0	f
52	621	235	0	f
53	621	265	0	f
54	621	266	0	f
55	621	267	0	f
56	621	349	0	f
57	621	350	0	f
58	621	351	0	f
59	621	353	0	f
60	621	352	0	f
61	621	354	0	f
62	621	355	0	f
63	621	356	0	f
64	621	359	0	f
65	621	360	0	f
66	621	361	0	f
67	621	362	0	f
68	621	363	0	f
69	621	364	0	f
70	621	365	0	f
71	621	366	0	f
72	621	367	0	f
73	621	368	0	f
74	621	369	0	f
75	621	515	0	f
76	621	516	0	f
77	621	517	0	f
78	621	518	0	f
79	621	519	0	f
80	621	520	0	f
81	621	521	0	f
82	621	576	0	f
83	621	577	0	f
84	621	578	0	f
85	621	580	0	f
86	621	615	0	f
87	621	616	0	f
88	621	617	0	f
89	621	618	0	f
90	621	619	0	f
91	621	620	0	f
92	621	626	0	f
93	621	630	0	f
94	632	631	0	f
\.


--
-- Name: relationship_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('relationship_id_seq', 94, true);


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY student (id, name, surname, handle, address, password, salt, isadmin) FROM stdin;
1	Admin	Nilson	admin	Centauri	77e05fb19ddc6b52c4902689b662f76fe9f7097e4ea4468ed65716596f6026a1	]^\\]	t
2	hej	hej	hej	hej	b83e99ba77494025c3d78861ae2b26832dbee60d479e1a1ae4c84743bd89602e	`]_a	f
3	ZAP	ZAP	ZAP	ZAP	38a3a2694a972a819e66029e48a887a2e8e01b762ef4fa4cc01434d4b225924e	```a	f
623	Christian	Lyngbye	clyn	Kbh Øst	972fe1047fea209a2889b8c3befb165c81cf37b725980f0c2b23fe5065edd8f3	^^^^	f
624	deep	penetration	dp	xxx	655f2d51e10c6b55e5d6ded9e969d74f9b45971ec63f26b4050e82326faf3151	\\`]]	f
625	rasmusgreve2	greve	rasmusgreve2	qweqwe	3795a95ae3527ca2edc9423b4f9282b1dfcab84386baafcddf0b1f89df2af02a	]a]^	f
626	fresh 	prince of bel-air	saywhat	west philadelphia	75c947070458929f44797cb5bca3afafefaec8ca0824cfb5d00998c4f104042a	\\_a]	f
630	t	t	t	t	b0dd7416b29bbb5e7c761a10239eb91e6cc8b0064307f5fcb53129f960d6f3c8	]^]^	f
631	Test	Testy	hillemænd	Fake street 9000	d735aeffd2720f3722258ab7e084e0568ee81ba1318f913f07e6d8802cdd6a46	`\\\\^	f
349	ZAP	ZAP	'	ZAP	ed56af894c0ac55f664af5118ab4a5b52d96bf07fb4f58f3f4277993b2fd7725	`]\\]	f
350	ZAP	ZAP	ZAP'	ZAP	f4ed8fdf4a685e7f5e60d1bddd9b30637f9277a8e5a4b9cd4b10a3616f191c2b	a_aa	f
351	ZAP	ZAP	"	ZAP	476a1f32126f7362330af7a3b3028fbac381fc3e7d23d36b4e5b3eecd77ead0b	_a__	f
43	ZAP	ZAP	/etc/passwd	ZAP	926b376bb551f4865a0cc93c82fd295cbb24e481470fc3dd86301d372547254a	\\a\\a	f
44	ZAP	ZAP	/Windows\\system.ini	ZAP	c49204ce493a220730318736c72c3fc2ff5d9b61eb2184b59c6d4b3c505a2d6f	aa\\]	f
45	ZAP	ZAP	/WEB-INF/web.xml	ZAP	4d62e2e90c9f99d9825e09ccda78244c71daa88a64168f83a564f7ef6f21348b	^a^^	f
46	ZAP	ZAP	\\etc/passwd	ZAP	e634d1c693b9020f76659fad6526714897f29375ed1641f85d42be4a419cbf80	`__]	f
47	ZAP	ZAP	\\Windows\\system.ini	ZAP	bd41d9f55bc6fd70df56855252e1cbad992087b0bfff956a80cb3841d5e8a2c3	^]\\a	f
48	ZAP	ZAP	\\WEB-INF/web.xml	ZAP	75fe397011f28fba6ae21cfd50d829b318ad060094aa7ed54fa8393ede2b6e83	^^_]	f
49	ZAP	ZAP	/../../../../../../../../../../../../../../../../../etc/passwd	ZAP	caa2d12c964ce918465fe1db10581bb6c62908be5fbe0c06d7770136eba56649	a^^`	f
50	ZAP	ZAP	/../../../../../../../../../../../../../../../../../Windows\\system.ini	ZAP	ec6e9a42e135800dca62854e6f44e02c423ac29f473970f74b38ecec3064281e	^]a]	f
51	ZAP	ZAP	/../../../../../../../../../../../../../../../../../WEB-INF/web.xml	ZAP	05b3b98993edae83ee850a98f506b3a4ff7a73327fc23ecfb40823a9367eb8c4	`a`]	f
52	ZAP	ZAP	\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\etc/passwd	ZAP	facae0d8e5850c6618fe76e3b6afffb90f56994c320b4272eaa8c18031df1066	a^]_	f
53	ZAP	ZAP	\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\Windows\\system.ini	ZAP	70c8aa13db2a2cb42b5788e10f8e8a66c508d092a86ae0047e67f4c25022016e	^_]]	f
54	ZAP	ZAP	\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\WEB-INF/web.xml	ZAP	6469c300cef78c501506dcf74d28bba260bb863ed1f67b9731e585357d499c66	a\\]a	f
352	ZAP	ZAP	ZAP"	ZAP	a6be123856c4f8b379bfebd14eee29d9fb113a934c3e4a14281f8b74b5784d2e	_]a^	f
353	ZAP	ZAP	)	ZAP	d4cfdeffe489c6e1dd3637cf7c2de5fdbdc0ea6e02ded4415d083135bd866903	]]^^	f
354	ZAP	ZAP	ZAP)	ZAP	579d85b87b437ce8d7482a833ce9908bccbb48d420993dc32f0f3552e039d113	a``]	f
355	ZAP	ZAP	(	ZAP	68210dc376f613f5ee2d74ae5431052c24767e0d5308c9b35cb8f92d23de7b6b	\\`a_	f
356	ZAP	ZAP	ZAP(	ZAP	cdaa869ea27e30f7c1c7b7c1a8f1c1923123f8038cd87acd5b953aae7b6a7d46	\\^\\^	f
55	ZAP	ZAP	thishouldnotexistandhopefullyitwillnot	ZAP	781d210113b14b1528fa7a1de4d5ac2ff5f4752bd29b18f3bdbc3e18328db12b	aa^\\	f
359	ZAP	ZAP	ZAP AND 1=1 -- 	ZAP	55eaa1214a16b2d7d72fc5a9bb96d5b21108b584476deac8d4bc0dfa8c4dce94	a`a^	f
360	ZAP	ZAP	ZAP' AND '1'='1' -- 	ZAP	3bb5ce79a0282b0aa90b17f9673fd1da60e848b32a794f1ea7e89bb07fcb6afd	`]\\_	f
361	ZAP	ZAP	ZAP" AND "1"="1" -- 	ZAP	980cf61803b8dc78eff486d9ad1bd77897b19c4905c27fb5a12783a72c10870a	]]`\\	f
362	ZAP	ZAP	ZAP AND 1=1	ZAP	2e8a4ffc33c418d89058a6a16990d62f65d34ad6b0a935f379d093892b9f7cba	a^]^	f
363	ZAP	ZAP	ZAP' AND '1'='1	ZAP	1c2585232ec822a069af52316b5237be2914178a07748b4923539e5bd0d0c11e	\\`a\\	f
364	ZAP	ZAP	ZAP" AND "1"="1	ZAP	e7155363da33c5d5c03b5e829e1dc9e7d507d6c0088f3232b23bdae82f704eba	\\]\\a	f
365	ZAP	ZAP	ZAP UNION ALL select NULL -- 	ZAP	cb4e4db00fe410c7e7d9d5b00ff8f092f438e3c56a75c4658488fc60ff0f402f	]_`_	f
366	ZAP	ZAP	ZAP' UNION ALL select NULL -- 	ZAP	49527f2f7a5199c58ee309929732abcf0fb020ecfa963391f382bd8b0d516168	\\a^`	f
367	ZAP	ZAP	ZAP" UNION ALL select NULL -- 	ZAP	5e538ab8a2015af3b45542712d2eea8cb4fd743fc1bb038b03b40b54e6c0e670	``a]	f
368	ZAP	ZAP	ZAP) UNION ALL select NULL -- 	ZAP	7d41169e8a3c904eb9edcab342322a57741ecd13c8e39c70dd9fec849f86f4cb	\\a\\\\	f
369	ZAP	ZAP	ZAP') UNION ALL select NULL -- 	ZAP	6f001e367fe50d4882e0a874d869f7a122cbc0c59d3d0a47791ee54125b297ae	`\\\\^	f
576	ZAP	ZAP	\N	ZAP	7818b4e0393360bc6aba8468f824fe43bba9b181c8bfca8c12a9a3cff9a13f4d	_]_`	f
577	ZAP	ZAP	@	ZAP	2a2e2c20f46978a9ee83598a8c47d31cf373384bcb20455daeec4358e87d247e	\\\\^]	f
578	ZAP	ZAP	+	ZAP	1b68a3b0f3a6cb9a82bfd5cdb9d2cb45f34e57a829db0e94af1373ff62aed20d	_]\\`	f
580	ZAP	ZAP	|	ZAP	3b8f71638108f7ac24150d1f0de17ed901d1553aa4690dde0b11f989c806ac43	aa_`	f
214	ZAP	ZAP	http://www.owasp.org	ZAP	02f46ae26dea88f93cd326aca4d4151d24d0b44f19361bdac76f809a4bcfbb60	^___	f
614	jacob	fischer	1234	jaco@itu.dk	f888af4bee7389daf6a9ffde89753a35e10c12caec0ec6a3b36fc652ac2e297b	^_a_	f
615	Erikerik	Erik	Erik	Erik	fcb2615873210d13c0d482cfc173a3484993c08fa0f50d2d6e6195af48ae0d64	aaa^	f
616	Someone	ReallyHandsome	Handle	Somewhere	797a84955898045b3b4f4ec02e1abedcc21bc160806a88848a3cce9e289d98cb	^_a\\	f
617	Blabla	Blabla	Blabla	Blabla	f36253639e1c1dfce1fedf5e07df504e0326778e588e881a10a0f2d5eeaa5f9f	__`\\	f
618	frank	jachola	what	mehmet	230aa0ab59321db9350ea693062471c1d9290a8595f70fd9a3037b26037f7d23	_^`]	f
619	lala	lala	user1	bla	e2bf0054ab6c77da0482acd4740b6bb1fa6e1d65b39b056efc38beaa7f4d1d84	\\a]_	f
620	lala	lala	user2	Rainbow Road	f29ba93fb7a81af620680e978bd071c2f43f5ae0ad0ef47ef1dc78839f823cd1	_^_\\	f
621	Rasmus	Greve	rasmusgreve	Byvej 10	a7167b96026226bc4f9c099a29d5554bee5edf5a6f36dd0eb687fe6de772ec41	`a]a	f
622	ivaylo	sharkov	ivo	bla123	75d8c07305593e0fa3cf6f48df5d2956905a8daa1e587f576cdba773914fbd25	``]\\	f
632	Hans	Osteklokke	Ørlehans	Stiknarkoman 12	e0918b265d8463e3ca531e51c50d3f5600ea414397b59cbda8384a7a925f93f4	`\\_`	f
151	ZAP	ZAP	http://www.google.com/	ZAP	3e3b1ad968f0fa5f70f9c3d399d61bafd1813e1d2d70f6310fa721b122e756e4	^\\^\\	f
152	ZAP	ZAP	http://www.google.com:80/	ZAP	dbcfeb4620f24b227cc1cebf05eeac83f81e1d193781943a00aa4799b9600ef9	_a_\\	f
153	ZAP	ZAP	http://www.google.com	ZAP	5b2cbdf1d4a7d055878e13844e36f0cb3ff3625002c940ac5f9b81ee80c6aa7d	\\]a]	f
154	ZAP	ZAP	http://www.google.com/search?q=OWASP%20ZAP	ZAP	10e9b56b85675dfc788b3747af24b96f1719d90899ce31ac84e7ae51ac62259d	`\\]`	f
155	ZAP	ZAP	http://www.google.com:80/search?q=OWASP%20ZAP	ZAP	6e46cc5ffa81555d9c371f81ee3ad7d4c4d389853f95ca956fdec2e07c0cd1f3	]\\aa	f
156	ZAP	ZAP	www.google.com/	ZAP	b64bc44f3fcf58bb20f6aea947f74a07df578e777aba9c867a118f32f60877be	a^_]	f
157	ZAP	ZAP	www.google.com:80/	ZAP	a700423f7e46c53ffd54cfce35040eafd73fe750ade2713e24d787e4487c590b	`]^`	f
158	ZAP	ZAP	www.google.com	ZAP	32629aeeb0960faa23dee6dbf9b60147a2c076b63a78e317f4846a77956940db	]^a^	f
159	ZAP	ZAP	www.google.com/search?q=OWASP%20ZAP	ZAP	1b864411dc26fc438f99f9a4da81e31fb4ba97016e793861ee31eacf45455a14	^_a]	f
160	ZAP	ZAP	www.google.com:80/search?q=OWASP%20ZAP	ZAP	2818b34e8aaa608d9be42bf14d862acd65da9e04be21204cd73464caf189b0ba	\\^\\_	f
232	ZAP	ZAP	<!--#EXEC cmd="ls /"-->	ZAP	1f8b8b5a595e63af75c4faaca60484509b980ab9c36c873c4c214064a54ab9db	_]`a	f
233	ZAP	ZAP	"><!--#EXEC cmd="ls /"--><	ZAP	fe0c2929349f98bb9677a973d94f6c2ae6fabb31054b997f0594bb68557fca1b	_\\a_	f
234	ZAP	ZAP	<!--#EXEC cmd="dir \\"-->	ZAP	d9a3b31a202e028f41b18bf471cd6566abe642c8654ef2034762098a91750e40	]aa`	f
235	ZAP	ZAP	"><!--#EXEC cmd="dir \\"--><	ZAP	8aa1380e9bdb1a16f0e30ceffb83a51967e6bc3c519082ad3f55710d0c3b09ff	_``a	f
515	ZAP	ZAP	Set-cookie: Tamper=2918187384565502119	ZAP	9b2cf0032568a5a0c5dadf6511aec87f6738faee12304649e1e3b12436de65d1	\\]`a	f
516	ZAP	ZAP	any\r\nSet-cookie: Tamper=2918187384565502119	ZAP	88758287e76bf9d13f987f25bab04171a946258323fc490ee14048ac8bbca550	aaa`	f
517	ZAP	ZAP	any?\r\nSet-cookie: Tamper=2918187384565502119	ZAP	9b69dd87f8bca7df33736a4f6a283f70f8f43f34c6ee63cd9fae62c49bc4a792	^aa`	f
518	ZAP	ZAP	any\nSet-cookie: Tamper=2918187384565502119	ZAP	fa451016375dc223edc8e70b947a58e88db8f5eea3f967c9c3cb116f59c965f6	]a]a	f
519	ZAP	ZAP	any?\nSet-cookie: Tamper=2918187384565502119	ZAP	a7eaad9ccb31e1a2269ea35aaf24c4f9f367b775a6b5d733886d70b7a96dd072	`_]`	f
520	ZAP	ZAP	any\r\nSet-cookie: Tamper=2918187384565502119\r\n	ZAP	950c27d49bb708718d12f843aed541b4cbbdef47a8c3a2b7ad52c5abae5d357a	``^]	f
521	ZAP	ZAP	any?\r\nSet-cookie: Tamper=2918187384565502119\r\n	ZAP	a5e07e6a1718f1ffdb7df1b83b26be1cb2f5d913ca062ed13b2cb47193ee4614	]a``	f
265	ZAP	ZAP	0W45pz4p	ZAP	0669a57c7d8f77ee50304719a54656d7d27ee2e94784229a7f87464804fa21cc	a`^_	f
266	ZAP	ZAP	"><script>alert(1);</script>	ZAP	68cd5e48810de96db7a2ceda79112cfdd3aa79ed0c1823236753d8c081d9bca2	`^a_	f
267	ZAP	ZAP	" onMouseOver="alert(1);	ZAP	10ffae0d215e8271a020149274f2cd8e04491c2a5df04447889bce96df122ef7	^a_\\	f
\.


--
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('student_id_seq', 632, true);


--
-- Name: hobby_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hobby
    ADD CONSTRAINT hobby_id_key UNIQUE (id);


--
-- Name: hobby_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hobby
    ADD CONSTRAINT hobby_primary PRIMARY KEY (id);


--
-- Name: hug_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hug
    ADD CONSTRAINT hug_id_key UNIQUE (id);


--
-- Name: hug_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hug
    ADD CONSTRAINT hug_primary PRIMARY KEY (id);


--
-- Name: interest_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY interest
    ADD CONSTRAINT interest_id_key UNIQUE (id);


--
-- Name: interest_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY interest
    ADD CONSTRAINT interest_primary PRIMARY KEY (id);


--
-- Name: primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY student
    ADD CONSTRAINT "primary" PRIMARY KEY (id);


--
-- Name: relationship_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY relationship
    ADD CONSTRAINT relationship_id_key UNIQUE (id);


--
-- Name: relationship_primary; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY relationship
    ADD CONSTRAINT relationship_primary PRIMARY KEY (id);


--
-- Name: student_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_id_key UNIQUE (id);


--
-- Name: unique_handle; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY student
    ADD CONSTRAINT unique_handle UNIQUE (handle);


--
-- Name: hobby1_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY interest
    ADD CONSTRAINT hobby1_id FOREIGN KEY (hobby) REFERENCES hobby(id);


--
-- Name: student1_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY relationship
    ADD CONSTRAINT student1_id FOREIGN KEY (student1) REFERENCES student(id);


--
-- Name: student2_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY relationship
    ADD CONSTRAINT student2_id FOREIGN KEY (student2) REFERENCES student(id);


--
-- Name: student3_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY interest
    ADD CONSTRAINT student3_id FOREIGN KEY (student) REFERENCES student(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT USAGE ON SCHEMA public TO tomcat;


--
-- Name: hobby; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE hobby FROM PUBLIC;
REVOKE ALL ON TABLE hobby FROM postgres;
GRANT ALL ON TABLE hobby TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE hobby TO tomcat;


--
-- Name: hobby_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE hobby_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE hobby_id_seq FROM postgres;
GRANT ALL ON SEQUENCE hobby_id_seq TO postgres;
GRANT USAGE ON SEQUENCE hobby_id_seq TO tomcat;


--
-- Name: hug; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE hug FROM PUBLIC;
REVOKE ALL ON TABLE hug FROM postgres;
GRANT ALL ON TABLE hug TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE hug TO tomcat;


--
-- Name: hug_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE hug_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE hug_id_seq FROM postgres;
GRANT ALL ON SEQUENCE hug_id_seq TO postgres;
GRANT USAGE ON SEQUENCE hug_id_seq TO tomcat;


--
-- Name: interest; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE interest FROM PUBLIC;
REVOKE ALL ON TABLE interest FROM postgres;
GRANT ALL ON TABLE interest TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE interest TO tomcat;


--
-- Name: interest_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE interest_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE interest_id_seq FROM postgres;
GRANT ALL ON SEQUENCE interest_id_seq TO postgres;
GRANT USAGE ON SEQUENCE interest_id_seq TO tomcat;


--
-- Name: relationship; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE relationship FROM PUBLIC;
REVOKE ALL ON TABLE relationship FROM postgres;
GRANT ALL ON TABLE relationship TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE relationship TO tomcat;


--
-- Name: relationship_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE relationship_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE relationship_id_seq FROM postgres;
GRANT ALL ON SEQUENCE relationship_id_seq TO postgres;
GRANT USAGE ON SEQUENCE relationship_id_seq TO tomcat;


--
-- Name: student; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE student FROM PUBLIC;
REVOKE ALL ON TABLE student FROM postgres;
GRANT ALL ON TABLE student TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE student TO tomcat;


--
-- Name: student_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE student_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE student_id_seq FROM postgres;
GRANT ALL ON SEQUENCE student_id_seq TO postgres;
GRANT USAGE ON SEQUENCE student_id_seq TO tomcat;


--
-- PostgreSQL database dump complete
--

