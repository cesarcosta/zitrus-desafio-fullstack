Desafio FullStack

Construir uma aplicação RESTful para controle de estoque com no mínimo duas entidades, como por exemplo: Produto e Movimento Estoque.

Entidades:
	
	Produto:
		• Código -ok
		• Descrição -ok
		• Tipo Produto (Eletrônico, eletrodoméstico, móvel)
		• Valor no Fornecedor -ok
		• Quantidade em estoque -ok

	Movimento Estoque:
		• Produto
		• Tipo Movimentação (Entrada/Saída)
		• Valor de venda
		• Data de venda
		• Quantidade movimentada

Uso de Tecnologias, escolha conforme opções abaixo.

Tecnologias BackEnd:
	• Java Spring Boot

Tecnologias FrontEnd:
	• Vue.js
	• Angular
	• React


O mínimo esperado da aplicação:
	• Boas práticas de desenvolvimento e clean code;

	• CRUD - (Create, Read, Update, Delete) de produtos;

	• Efetuar entrada e saída dos produtos no estoque. É importante validar o saldo ao efetuar uma saída do produto, caso não haja quantidade suficiente, deve ser retornado uma mensagem específica;

	• Consulta de produtos por tipo, com quantidade de saída e quantidade disponível;

	• Consulta de lucro por produto, exibindo a quantidade total de saída, e total do lucro (valor de venda – valor do fornecedor).

Não é obrigatório, mas se quiser surpreender:
	• Teste unitário.

Observações:
	• Para facilitar a criação de uma aplicação utilizando Spring Boot, acesse o Spring Initializr(https://start.spring.io/). 

	• Para o banco de dados, uma sugestão é utilizar o H2 (banco de dados em memória), mas fique à vontade para utilizar o processo que achar mais adequado.

	• Para submissão do caso de teste, utilize o github como repositório, e envie o link para o e-mail: lais.armond@zitrus.com.br