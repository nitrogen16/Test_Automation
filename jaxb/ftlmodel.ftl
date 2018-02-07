<Libraries>
    <Library>
        <#list bookDetails as book>
            <Book id="${book.id}">	
                <BookName>${book.bookName}</BookName>
                <BookAuthor>${book.bookAuthor}</BookAuthor>
            </Book>
        </#list>
    </Library>
</Libraries>