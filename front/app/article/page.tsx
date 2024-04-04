'use client'

import { useEffect, useState } from "react";


export default function Article() {

    const [articleList, setArticleList] = useState([]);

    useEffect(() => {
        fetchArticle();
    }, [])

    const fetchArticle = () => {
        fetch("http://localhost:8090/api/v1/articles")
            .then(data => data.json())
            .then(result => setArticleList(result.data.articleList));
        console.log(articleList);
    }


    return (
        < >
            {articleList.map(article =>
                <>
                    <li>{article.id}</li>
                    <li>{article.title}</li>
                    <li>{article.createdDate}</li>
                </>

            )}
        </>
    );
}
