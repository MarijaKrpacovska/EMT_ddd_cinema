import React, {useState} from "react";
import {Link} from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import './scheduledMovie.css';
import scheduledMovie from "../../Ticket/ConfirmedReservationsList/confirmedReservations";

const ScheduledMovie = (props) => {
    //console.log("SELECTED MOVIE" +props.movies(0).id.id)

    const [pageNumber, setPageNumber] = useState(0);
    const itemsPerPage = 6;
    const pageVisited = pageNumber * itemsPerPage;
    const pageCount = Math.ceil(props?.scheduledMovies?.length / itemsPerPage);

    const handlePageChange = ({selected}) => {
        setPageNumber(selected);
    }

    const displayScheduledMovies = props.scheduledMovies
        .slice(pageVisited, pageVisited + itemsPerPage)
        .map((term) => {
            var myDate = new Date(term?.dateAndTimeScheduled?.date?.toString());
            return (
                <tr className={term?.scheduledMovieStatus==='CANCELED' ? "bg-danger text-light" : ""}>
                    <td>
                        <p>
                        {myDate.toString().substr(0,15)}, {term?.dateAndTimeScheduled?.hour?.toString()?.length === 1 ? "0"+term?.dateAndTimeScheduled?.hour?.toString() : term?.dateAndTimeScheduled?.hour?.toString()}:
                        {term?.dateAndTimeScheduled?.minutes?.toString()?.length === 1 ? "0"+term?.dateAndTimeScheduled?.minutes?.toString() : term?.dateAndTimeScheduled?.minutes?.toString()}
                        </p>
                        </td>
                    <td>{props?.movies?.map((obj) => {
                        if(obj?.id?.id === term?.movieId?.id){
                            return (
                                <p>{obj?.name}</p>
                            );
                        }
                    })}</td>
                    <td>{term?.ticketPrice?.amount} {term?.ticketPrice?.currency}</td>

                    <td> Sales: {term?.sales}</td>
                    <td>
                        <div hidden={term?.scheduledMovieStatus === "CANCELED"}>
                        <Link
                            className={"btn btn btn-block btn-dark scheduleMovieButton"}
                            onClick={() => props.onBookTickets(term?.id?.id)}
                            to={`/ticket/makeNewReservation/${term?.id?.id}`}>
                            Book tickets
                        </Link>
                        </div>
                    </td>
                    <td>
                        <div hidden={term?.scheduledMovieStatus === "CANCELED"}>
                        <Link
                            className={"btn btn-block btn-danger scheduleMovieButton"}
                            onClick={() => {
                                props.onCancelScheduledMove(term?.id?.id)
                            }}
                            to={`/scheduledMovies`}>
                            Cancel Scheduled Movie
                        </Link>
                        </div>
                    </td>
                    <td>
                        <div hidden={term?.scheduledMovieStatus === "CANCELED"}>
                        <Link
                            className={"btn btn-block btn-secondary scheduleMovieButton"}
                            onClick={() => {
                                props.onRescheduleMovie(term?.id?.id)
                            }}
                            to={`/scheduledMovies/reschedule/${term?.id?.id}`}>
                            Reschedule movie
                        </Link>
                        </div>
                    </td>
                </tr>
            );
        });

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <h3 className={"text-danger"}>
                    Movie Schedule:
                </h3>
                <hr/>
                <br/>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>

                        <thead>
                        <th>
                            Scheduled for
                        </th>
                        <th>
                            Movie
                        </th>
                        <th>
                            Ticket Price
                        </th>
                        <th>
                            Sales
                        </th>
                        </thead>
                        <tbody>
                        {displayScheduledMovies}
                        </tbody>
                    </table>
                </div>
            </div>


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
    );
}

export default ScheduledMovie;