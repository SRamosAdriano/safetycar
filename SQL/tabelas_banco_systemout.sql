USE systemout;

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  login varchar(255) NOT NULL,
  senha varchar(255) NOT NULL,
  primeiroLogin varchar(20) NOT NULL DEFAULT '1',
  dataCadastro datetime NOT NULL,
  dataUltimoLogin datetime DEFAULT NULL,
  erroLogin int(11) DEFAULT NULL,
  ativo varchar(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS grupos;
CREATE TABLE grupos (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  dataCadastro datetime NOT NULL,
  ativo varchar(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS paginas;
CREATE TABLE paginas (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  dataCadastro datetime NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS clifor;
CREATE TABLE clifor (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  tipoCliFor varchar(25) NOT NULL,
  nomeCompleto varchar(150) NOT NULL,
  rg varchar(20) NOT NULL,
  cpf varchar(20) NOT NULL,
  email varchar(255) DEFAULT NULL,
  dataCadastro date NOT NULL,
  status varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS combustivelveiculos;
CREATE TABLE combustivelveiculos (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  descricao varchar(80) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS contatos;
CREATE TABLE contatos (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  endereco varchar(255) DEFAULT NULL,
  dataNascimento date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS estados;
CREATE TABLE estados (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  sigla varchar(80) NOT NULL,
  descricao varchar(80) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS fornecedordespesas;
CREATE TABLE fornecedordespesas (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  contato varchar(100) NOT NULL,
  telefone varchar(25) DEFAULT NULL,
  dtCadastro date NOT NULL,
  ativo varchar(20) DEFAULT '1',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS funcionarios;
CREATE TABLE funcionarios (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  usuario varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS marcasveiculos;
CREATE TABLE marcasveiculos (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nomeMarca varchar(80) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tarefas;
CREATE TABLE tarefas (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  descricao varchar(256) DEFAULT NULL,
  finalizado tinyint(1) DEFAULT NULL,
  dataFinalizacao date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS veiculo;
CREATE TABLE veiculo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  placa varchar(10) NOT NULL,
  cidade varchar(150) NOT NULL,
  estado varchar(10) NOT NULL,
  marca varchar(100) NOT NULL,
  versao varchar(200) NOT NULL,
  modelo varchar(200) NOT NULL,
  anoFab bigint(20) unsigned NOT NULL,
  anoMod bigint(20) unsigned NOT NULL,
  renavan bigint(20) unsigned NOT NULL,
  chassi varchar(50) NOT NULL,
  km bigint(20) unsigned NOT NULL,
  combustivel varchar(80) NOT NULL,
  cor varchar(150) NOT NULL,
  dataCadastro date NOT NULL,
  status varchar(100) NOT NULL,
  infDocNome varchar(200) NOT NULL,
  infDocEndereco varchar(200) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS vendedor;
CREATE TABLE vendedor (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(80) NOT NULL,
  sobreNome varchar(150) DEFAULT NULL,
  dataCadastro date NOT NULL,
  ativo varchar(20) DEFAULT '1',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS associado_grupos_usuarios;
CREATE TABLE associado_grupos_usuarios (
  usuario_id bigint(20) NOT NULL,
  grupo_id bigint(20) NOT NULL,
  KEY usuario_id (usuario_id),
  KEY grupo_id (grupo_id),
  CONSTRAINT associado_grupos_usuarios_ibfk_1 FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
  CONSTRAINT associado_grupos_usuarios_ibfk_2 FOREIGN KEY (grupo_id) REFERENCES grupos (id)
);

DROP TABLE IF EXISTS associado_paginas_grupos;
CREATE TABLE associado_paginas_grupos (
  grupo_id bigint(20) NOT NULL,
  pagina_id bigint(20) NOT NULL,
  KEY grupo_id (grupo_id),
  KEY pagina_id (pagina_id),
  CONSTRAINT associado_paginas_grupos_ibfk_1 FOREIGN KEY (grupo_id) REFERENCES grupos (id),
  CONSTRAINT associado_paginas_grupos_ibfk_2 FOREIGN KEY (pagina_id) REFERENCES paginas (id)
);

DROP TABLE IF EXISTS associado_paginas_url;
CREATE TABLE associado_paginas_url (
  url varchar(255) NOT NULL,
  pagina_id bigint(20) NOT NULL,
  KEY pagina_id (pagina_id),
  CONSTRAINT associado_paginas_url_ibfk_1 FOREIGN KEY (pagina_id) REFERENCES paginas (id)
);

DROP TABLE IF EXISTS compraveiculo;
CREATE TABLE compraveiculo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dataCompra date NOT NULL,
  valorCompra decimal(12,2) NOT NULL,
  consignado tinyint(1) NOT NULL,
  veiculo_id bigint(20) NOT NULL,
  fornecedor_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  KEY veiculo_id (veiculo_id),
  KEY fornecedor_id (fornecedor_id),
  CONSTRAINT compraveiculo_ibfk_1 FOREIGN KEY (veiculo_id) REFERENCES veiculo (id),
  CONSTRAINT compraveiculo_ibfk_2 FOREIGN KEY (fornecedor_id) REFERENCES clifor (id)
);

DROP TABLE IF EXISTS enderecoclifor;
CREATE TABLE enderecoclifor (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  endereco varchar(150) NOT NULL,
  bairro varchar(150) NOT NULL,
  cep varchar(20) NOT NULL,
  cidade varchar(150) NOT NULL,
  estado varchar(3) NOT NULL,
  clifor_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  KEY clifor_id (clifor_id),
  CONSTRAINT enderecoclifor_ibfk_1 FOREIGN KEY (clifor_id) REFERENCES clifor (id)
);

DROP TABLE IF EXISTS gastosveiculos;
CREATE TABLE gastosveiculos (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  descricao varchar(100) NOT NULL,
  valor decimal(12,2) NOT NULL,
  dtLancamento date NOT NULL,
  veiculo_id bigint(20) NOT NULL,
  fornecedordespesas_id bigint(20) NOT NULL,
  nDocumento varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY veiculo_id (veiculo_id),
  KEY fornecedordespesas_id (fornecedordespesas_id),
  CONSTRAINT gastosveiculos_ibfk_1 FOREIGN KEY (veiculo_id) REFERENCES veiculo (id),
  CONSTRAINT gastosveiculos_ibfk_2 FOREIGN KEY (fornecedordespesas_id) REFERENCES fornecedordespesas (id)
);

DROP TABLE IF EXISTS opcionaisveiculo;
CREATE TABLE opcionaisveiculo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  veiculo_id bigint(20) NOT NULL,
  opCompleto tinyint(1) DEFAULT NULL,
  opArQuente tinyint(1) DEFAULT NULL,
  opFarolXenon tinyint(1) DEFAULT NULL,
  opBancoRegAltura tinyint(1) DEFAULT NULL,
  opGps tinyint(1) DEFAULT NULL,
  opLimpTraseiro tinyint(1) DEFAULT NULL,
  opTravaEletrica tinyint(1) DEFAULT NULL,
  opRadio tinyint(1) DEFAULT NULL,
  opCapotaMaritima tinyint(1) DEFAULT NULL,
  opRadioTocaFita tinyint(1) DEFAULT NULL,
  opCdMp3 tinyint(1) DEFAULT NULL,
  opCompBordo tinyint(1) DEFAULT NULL,
  opRetroFotocromico tinyint(1) DEFAULT NULL,
  opRetrovEletrico tinyint(1) DEFAULT NULL,
  opRodLigaLeve tinyint(1) DEFAULT NULL,
  opSensorEstacionamento tinyint(1) DEFAULT NULL,
  opDVD tinyint(1) DEFAULT NULL,
  opTetoSolar tinyint(1) DEFAULT NULL,
  opDesembTraseito tinyint(1) DEFAULT NULL,
  opTracao4x4 tinyint(1) DEFAULT NULL,
  opAirBag tinyint(1) DEFAULT NULL,
  opVolRegAltura tinyint(1) DEFAULT NULL,
  opABS tinyint(1) DEFAULT NULL,
  opAlarme tinyint(1) DEFAULT NULL,
  opDirecaoHidraulica tinyint(1) DEFAULT NULL,
  opBancoCouro tinyint(1) DEFAULT NULL,
  opArCondicionado tinyint(1) DEFAULT NULL,
  opVidroEletrico tinyint(1) DEFAULT NULL,
  opChaveReserva tinyint(1) DEFAULT NULL,
  opManual tinyint(1) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY veiculo_id (veiculo_id),
  CONSTRAINT opcionaisveiculo_ibfk_1 FOREIGN KEY (veiculo_id) REFERENCES veiculo (id)
);

DROP TABLE IF EXISTS telefoneclifor;
CREATE TABLE telefoneclifor (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  telefone varchar(25) NOT NULL,
  tipo varchar(25) NOT NULL,
  clifor_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  KEY clifor_id (clifor_id),
  CONSTRAINT telefoneclifor_ibfk_1 FOREIGN KEY (clifor_id) REFERENCES clifor (id)
);

DROP TABLE IF EXISTS vendaveiculo;
CREATE TABLE vendaveiculo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dataVenda date NOT NULL,
  valorVenda decimal(12,2) NOT NULL,
  veiculo_id bigint(20) NOT NULL,
  cliente_id bigint(20) NOT NULL,
  vendedor_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  KEY veiculo_id (veiculo_id),
  KEY cliente_id (cliente_id),
  KEY vendedor_id (vendedor_id),
  CONSTRAINT vendaveiculo_ibfk_1 FOREIGN KEY (veiculo_id) REFERENCES veiculo (id),
  CONSTRAINT vendaveiculo_ibfk_2 FOREIGN KEY (cliente_id) REFERENCES clifor (id),
  CONSTRAINT vendaveiculo_ibfk_3 FOREIGN KEY (vendedor_id) REFERENCES vendedor (id)
);

DROP TABLE IF EXISTS vlcomissaoevendaveiculo;
CREATE TABLE vlcomissaoevendaveiculo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  valorComissao decimal(12,2) DEFAULT NULL,
  valorBonus decimal(12,2) DEFAULT NULL,
  valorVenda decimal(12,2) NOT NULL,
  veiculo_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  KEY veiculo_id (veiculo_id),
  CONSTRAINT vlcomissaoevendaveiculo_ibfk_1 FOREIGN KEY (veiculo_id) REFERENCES veiculo (id)
);