import './movies.css';
import React, {useEffect, useState} from "react";
import {Link} from 'react-router-dom';
import movies from "./movies";
import ReactPaginate from "react-paginate";

const Movies = (props) => {

    useEffect(() => {
        // const images = ["https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg",
        //     "https://assets.mubicdn.net/images/notebook/post_images/33421/images-w1400.jpg?1625400709",
        //     "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg"]
        if (props.movies.length === 0) {
            console.log("null" + props.movies.length)
        } else {
            const interval = setInterval(() => {
                let index = Math.floor(Math.random() * (props.movies.length));
                document.getElementById("imgImg").src = props.movies[index].moviePoster.imageUrl;
            }, 2000);
            return () => clearInterval(interval);
        }

    }, [props]);

    const [pageNumber, setPageNumber] = useState(0);
    const itemsPerPage = 3;
    const pageVisited = pageNumber * itemsPerPage;
    const pageCount = Math.ceil(props.movies.length / itemsPerPage);
    const randomNum = Math.floor(Math.random() * (props.movies.length));
    const changingImgUrl = props.movies.map((item) => {
        return item.url;
    });

    const handlePageChange = ({selected}) => {
        setPageNumber(selected);
    }

    const showRating = (movie) => {

        console.log(movie.rating.rating);
        const stars = [];
        for (let i = 0; i < movie.rating.rating; i++) {
            stars.push(<img height={"20px"} src={"https://i.imgur.com/fhFxIiJ.png"}></img>);
        }
        for (let i = Math.ceil(movie.rating.rating); i < 10; i++) {
            stars.push(<img height={"20px"} src={"https://i.imgur.com/fwE9Dya.png"}></img>);
        }
        console.log(stars);
        return <div className="stars">{stars}</div>;
        //return <p>bla</p>;
    };

    const displayMovies = props.movies
        .slice(pageVisited, pageVisited + itemsPerPage)
        .map((term) => {
            var myDate = new Date(term.publishDate.toString());
            return (
                <tr className={"p-1 mt-5"}>

                    {/*<Link*/}
                    {/*    onClick={() => props.onDetails(term.id.id)}*/}
                    {/*    to={`/movie/findById/${term.id.id}`}>*/}
                    {/*    <img className={"mt-1 mb-1"} src={term.url} height={"200px"} width={"136px"}></img>*/}
                    {/*</Link>*/}
                    <Link
                        onClick={() => {
                            props.onDetails(term.id.id);
                            props.onFetchScheduledMoviesByMovieId(term.id.id)
                            props.onFindSimilarMovies(term.genre)
                        }}
                        to={`/movie/fetchScheduledMoviesByMovieId/${term.id.id}`}>
                        <img className={"mt-1 mb-1"} src={term.moviePoster.imageUrl} height={"200px"}
                             width={"136px"}></img>
                    </Link>
                    <td className={"firstTd"}>
                        <Link
                            className={"text-black detailsLink"}
                            onClick={() => {
                                props.onDetails(term.id.id);
                                props.onFetchScheduledMoviesByMovieId(term.id.id)
                            }}
                            to={`/movie/fetchScheduledMoviesByMovieId/${term.id.id}`}>
                            <div>
                                <div className={"row"}>
                                    <div className={"col-md-7"}>
                                        <h3 className={"mb-3"}>
                                            {term.name} <small
                                            className={"font-weight-light movieGenre"}>({term.genre})</small>
                                        </h3>
                                    </div>
                                    <div className={"col rateMovieText"}>
                                        {showRating(term)}
                                        <Link className={"rateMovieLink"} onClick={() => props.onRateMovie(term.id.id)}
                                              to={`/movie/rateMovie/${term.id.id}`}>
                                            Rate movie
                                        </Link>
                                    </div>
                                </div>
                                {term.description}
                                <br/>
                                <b>Length:</b> {term.movieLength.length} {term.movieLength.unitOfTime}
                                <br/>
                                <b>Released on:</b> {myDate.toString().substr(0, 15)}
                                <br/>
                                <br/>
                                <div className={"row text-center"}>

                                    <div className={"col-sm-6 col-md-6"}></div>
                                    <div className="col-sm-3 col-md-3 buttonDiv">
                                        <Link className={"btn btn-sm btn-block btn-dark scheduleMovieButton"}
                                              onClick={() => props.onScheduleMovie(term.id.id)}
                                              to={`/scheduledMovies/add/${term.id.id}`}>
                                            Schedule movie
                                        </Link>
                                    </div>

                                    <div className="col-sm-3 col-md-3 text-sm">
                                        <i>Currently scheduled {term.numberOfTimesScheduled} times</i>
                                    </div>
                                    {/*<Link*/}
                                    {/*    onClick={() => { props.onDetails(term.id.id); props.onFetchScheduledMoviesByMovieId(term.id.id)}}*/}
                                    {/*    to={`/movie/fetchScheduledMoviesByMovieId/${term.id.id}`}>*/}
                                    {/*    Details with sm*/}
                                    {/*</Link>*/}
                                </div>
                            </div>
                        </Link>
                    </td>
                </tr>
            );
        });

    return (
        <div className={"container mm-4"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <div className={"row"}>
                        <div className={"col-md-8 mt-5 mr-5"}>

                            <div className="row">
                                <div className={"col-md-8"}>
                                    <h1 className={"text-danger"}>
                                        Current Movies
                                    </h1>
                                </div>
                                <div className="col-sm-4 col-md-4 buttonDiv">
                                    <Link className={"btn btn-lg btn-block btn-dark"} to={"/movie/add"}>Add Movie</Link>
                                </div>
                            </div>
                            <hr/>
                            <table className={"table"}>
                                <thead>
                                </thead>
                                <tbody>
                                {displayMovies}
                                <tr></tr>
                                </tbody>

                            </table>
                            <div className="col mb-3">
                                <ReactPaginate previousLabel={"back"}
                                               nextLabel={"next"}
                                               breakLabel={<a className={"page-link"} href="/#">...</a>}
                                               breakClassName={'page-item'}
                                               breakLinkClassName={'page-link'}
                                               containerClassName={'pagination m-4 justify-content-center'}
                                               pageClassName={'page-item'}
                                               pageLinkClassName={'page-link'}
                                               previousClassName={'page-item'}
                                               previousLinkClassName={'page-link'}
                                               nextClassName={'page-item'}
                                               nextLinkClassName={'page-link'}
                                               activeClassName={'active'}
                                               pageCount={pageCount}
                                               marginPagesDisplayed={3}
                                               pageRangeDisplayed={5}
                                               onPageChange={handlePageChange}/>
                            </div>

                        </div>
                        <div className={"col-md-1"}></div>
                        <div className={"col-md-3 ml-2 imgDiv mb-0"} id={"imgDiv"}>
                            {/*fix if empty*/}
                            <img id={"imgImg"} className={"imgImg"} height={"1100px"} src={changingImgUrl}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Movies;
