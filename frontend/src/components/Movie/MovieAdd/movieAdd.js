import './movieAdd.css';
import React from 'react';
import {useHistory} from 'react-router-dom';

//todo: fix warnings in console
const MovieAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        movieLength: {
            length: 0.0,
            unitOfTime: "min"
        },
        genre: "action",
        publishDate: "2021-01-01",
        description: "desc",
        moviePoster : {
            imageUrl: "",
            imageType: "poster"
        },
        movieAdvertisementImage : {
            imageUrl: "",
            imageType: "advertisement"
        },
        trailerUrl: "",
        scheduledMovies: []
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const publishDate = formData.publishDate;
        const description = formData.description;
        const movieLength = {
                length: formData.movieLength,
                unitOfTime: "min"
            };
        const genre= formData.genre;

        const moviePoster = {
            imageUrl: formData.moviePoster,
                imageType: "poster"
        };
        const movieAdvertisementImage = {
            imageUrl: formData.movieAdvertisementImage,
            imageType: "advertisement"
        };
        const trailerUrl = formData.trailerUrl

        props.onAddMovie(name,movieLength,genre, publishDate,description,moviePoster,movieAdvertisementImage,trailerUrl);
        history.push("/movie");
    }

    return(
        <div className="row mt-5 addRow">
            <div className="col-md-6">
                <h2 className={"text-danger text-center"}>
                    Add Movie:
                </h2>
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Name: </label>
                        <input type="text"
                               className="form-control mt-1"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter movie name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Genre</label>
                        <select name="genre" className="form-control mt-1" onChange={handleChange}>
                            <option value="action">Action</option>
                            <option value="romance">Romance</option>
                            <option value="horror">Horror</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="publishDate">Published on: </label>
                        <input type="date"
                               className="form-control m-1"
                               id="publishDate"
                               name="publishDate"
                               required
                               placeholder="Enter Movie publishDate"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Short description</label>
                        <textarea type="text"
                               className="form-control m-1"
                               id="description"
                               name="description"
                               placeholder="Enter short movie description"
                                  maxlength="1000"
                               required
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="movieLength">Length of Movie: <a className="tooltipp" title="Insert an image for an ad for the movie. This picture will be used for the home page, so the width should be greater than the height." href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 className="bi bi-question-circle" viewBox="0 0 16 16">
                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                <path
                                    d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
                            </svg>
                        </a></label>
                        <input type="number"
                               className="form-control m-1"
                               id="movieLength"
                               name="movieLength"
                               placeholder="Enter length of movie in minutes"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="moviePoster">Movie Poster: <a className="tooltipp" title="Insert an image of the poster for the movie. The movie poster's height should be greater then the width." href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 className="bi bi-question-circle" viewBox="0 0 16 16">
                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                <path
                                    d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
                            </svg>
                        </a></label>
                        <input type="text"
                               className="form-control m-1"
                               id="moviePoster"
                               name="moviePoster"
                               required
                               placeholder="Enter url for a movie poster image"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="movieAdvertisementImage">Movie Advertisement</label>
                        <input type="text"
                               className="form-control m-1"
                               id="movieAdvertisementImage"
                               name="movieAdvertisementImage"
                               required
                               placeholder="Enter url for a movie advertisement image"
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="trailerUrl">Movie Trailer</label> <a className="tooltipp" title="Open the youtube video, right-click it and select 'Copy video URL'. Paste the url here." href="#">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             className="bi bi-question-circle" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                            <path
                                d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
                        </svg>
                    </a>
                        <input type="text"
                               className="form-control m-1"
                               id="trailerUrl"
                               name="trailerUrl"
                               required
                               placeholder="Enter url for movie trailer (youtube video)"
                               onChange={handleChange}
                        />
                    </div>
                    <br/>

                    <div className={"text-center"}>
                    <button id="submit" type="submit" className="btn btn-dark align-self-md-center">Submit</button>
                    </div>
                    <br/>
                    </form>
            </div>
            <div className={"col-md-6 imageDiv"}>
                <img height={"600px"} width={"600px"} src={"https://www.pngall.com/wp-content/uploads/2018/06/Cinema-Transparent.png"}/>
            </div>

        </div>
    )
}

export default MovieAdd;
