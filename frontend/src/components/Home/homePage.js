import React, {useEffect} from "react";
import './homePage.css';
import 'react-slideshow-image/dist/styles.css';
import {Slide} from 'react-slideshow-image';
import {Link} from 'react-router-dom';
import movies from "../Movie/MovieList/movies";

const slideImages = [
    {
        url: "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2luZW1hfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80",
        caption: 'Regular Program',
        slideNumber: 1,
        link: '/movie'
    },
    {
        url: 'https://wallpaperboat.com/wp-content/uploads/2020/10/07/56115/halloween-21.jpg',
        caption: 'Halloween Program',
        slideNumber: 2,
        link: '/movie'
    },
    {
        url: 'https://cdn.wallpapersafari.com/91/33/RXTgcW.jpg',
        caption: 'Christmas Program (Coming soon)',
        slideNumber: 3,
        link: '/movie'
    }
];

const homePage = (props) => {

    const showRating = (movie) => {

        console.log(movie.rating.rating);
        const stars = [];
        for (let i=0;i<movie.rating.rating;i++) {
            stars.push(<img height={"20px"}  src={"https://i.imgur.com/fhFxIiJ.png"}></img>);
        }
        for (let i=Math.ceil(movie.rating.rating);i<10;i++) {
            stars.push(<img height={"20px"}  src={"https://i.imgur.com/fwE9Dya.png"}></img>);
        }
        console.log(stars);
        return <div className="stars">{stars}</div>;
        //return <p>bla</p>;
    };

    return (
        <div>
            <section>
                <div className="slide-container">
                    <Slide>
                        {props.movies.map((term) => (
                            <Link
                                onClick={() => { props.onDetails(term.id.id); props.onFetchScheduledMoviesByMovieId(term.id.id)}}
                                to={`/movie/fetchScheduledMoviesByMovieId/${term.id.id}`}>
                            <div hidden={props.movies.length === 0} className="each-slide" key={term.id.id}>
                                <div className={"slideDiv"} style={{'backgroundImage': `url(${term.movieAdvertisementImage.imageUrl})`}}>
                                    <span className={"slideText"}>
                                        <div className={"slideTextDiv  p-5"}>
                                            <div className={"row firstRow"}>
                                            <div className={"col"}>
                                                {term.name}
                                            </div>
                                            <div className={"col"}>
                                                 {showRating(term)}
                                            </div>
                                            </div>
                                            <div className={"row p-2"}>
                                            {term.description}
                                            </div>
                                        </div>

                                    </span>
                                </div>
                            </div>
                            </Link>
                        ))}
                    </Slide>
                </div>
            </section>
            <section className={"secondSection"}>
                <div className={"secondSectionMainDiv"}>
                <img src={"https://static.wixstatic.com/media/0c757c_6c977acc129b46d99c17d0c2322cd086~mv2.png/v1/fill/w_543,h_154,al_c,lg_1,q_85/0c757c_6c977acc129b46d99c17d0c2322cd086~mv2.webp"}/>
                <br/>
                    <div className={"aboutUsTextDiv"}>
                        <br/>
                    <i className={"aboutUsText font-weight-bold"}>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </i>
                </div>
                </div>
            </section>
            <section>
                <div className="slide-container">
                    <Slide>
                        {slideImages.map((slideImage, index) => (
                            <Link
                                to={slideImage.link}>
                            <div className="each-slide" key={index}>
                                <div className={"slideDiv"} style={{'backgroundImage': `url(${slideImage.url})`}}>
                                    <div>
                                        <span className={"slideText"}>
                                        <div className={"slideTextDiv  p-5"}>
                                            <div className={"row firstRow"}>
                                            <div className={"col"}>
                                                {slideImage.caption}
                                            </div>
                                            </div>
                                        </div>

                                    </span>
                                    </div>
                                </div>
                            </div>
                            </Link>
                        ))}
                    </Slide>
                </div>
            </section>
            <section className={"thirdSection bg-light"}>
                <div className={"secondSectionMainDiv"}>
                    <img src={"https://i.imgur.com/dN8T32Q.png"}/>

                    <div className={"aboutUsTextDiv contactTextDiv"}>
                        <i className={"aboutUsText font-weight-bold"}>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </i>
                    </div>
                </div>
            </section>
        </div>
    );
}

export default homePage;
