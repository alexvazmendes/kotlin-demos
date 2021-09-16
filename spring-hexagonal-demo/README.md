# Exemplo de aplicação Hexagonal

Essa aplicação é um exemplo de arquitetura hexagonal.
A ideia é apenas salvar e buscar livros.

* [Métodos HTTP](#métodos-http)
* [Arquitetura](#arquitetura)

## Métodos HTTP
Obs.: Ao enviar um identificador no header "Transaction-Id", o mesmo será exibido nos logs (caso não seja enviado será gerado um UUID aleatório)
```
18:14:56.065 [http-nio-8080-exec-3] [Transaction-ID=1234-1234] INFO  c.e.demo.adapter.web.BookController - Book saved. Id: f924ca04-7e68-49a7-b19b-53a31e474d6c 
```

#### POST: http://localhost:8080/books
- Exemplo de Requisição
```json
{
    "title": "Titulo 1",
    "author": "Autor 1",
    "publisher": "Editora 123",
    "year": 1998
}
```
- Resposta
```json
{
    "id": "303ec48c-6e45-436d-b7e3-3e4e8fc59267",
    "title": "Titulo 1"
}
```

#### GET: http://localhost:8080/books/{id}
- Resposta:
```json
{
    "id": "303ec48c-6e45-436d-b7e3-3e4e8fc59267",
    "title": "Titulo 1",
    "author": "Autor 1",
    "year": 1998,
    "publisher": "Editora 123",
    "createdAt": "2021-09-16T18:11:46.008595"
}
```
- Resposta em caso de livro no existente (status 404)
```json
{
    "message": "Book not found"
}
```
#### GET ALL: http://localhost:8080/books/
- Resposta
```json
[
    {
        "id": "100ef8d7-223c-4572-8723-1c970a21240d",
        "title": "Titulo 1",
        "author": "Autor 1",
        "year": 1998,
        "publisher": "Editora 123",
        "createdAt": "2021-09-16T18:14:38.945887"
    },
    {
        "id": "f924ca04-7e68-49a7-b19b-53a31e474d6c",
        "title": "Titulo 2",
        "author": "Autor 2",
        "year": 2010,
        "publisher": "Editora 321",
        "createdAt": "2021-09-16T18:14:56.061399"
    }
]
```

- Em caso de nenhum livro cadastrado será retornado 204 (No Content)

## Arquitetura
