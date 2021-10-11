// import React from 'react';
// import {useHistory} from 'react-router-dom';
//
// const ScheduleMovie = (props) => {
//
//     const history = useHistory();
//     const [formData, updateFormData] = React.useState({
//         //id: "",
//         startTime: {
//         hour: 0,
//             minutes: 0
//         },
//         endTime: {
//             hour: 0,
//                 minutes: 0
//         }
//     })
//
//     const handleChange = (e) => {
//         updateFormData({
//             ...formData,
//             [e.target.name]: e.target.value.trim()
//         })
//     }
//
//     const onFormSubmit = (e) => {
//         e.preventDefault();
//         const startTime= {
//             hour: 0,
//                 minutes: 0
//         };
//         const endTime= {
//             hour: 0,
//                 minutes: 0
//         }
//
//         props.onScheduleMovie(props.movie.id,startTime,endTime);
//         history.push("/movie");
//     }
//
//     return(
//         <div className="row mt-5">
//             <div className="col-md-5">
//                 <form onSubmit={onFormSubmit}>
//                     <div className="form-group">
//                         <label htmlFor="startTime">startTime</label>
//                         <input type="text"
//                                className="form-control"
//                                id="startTime"
//                                name="startTime"
//                                required
//                                placeholder="Enter Movie name"
//                                onChange={handleChange}
//                         />
//                     </div>
//                     <div className="form-group">
//                         <label htmlFor="endTime">endTime</label>
//                         <input type="text"
//                                className="form-control"
//                                id="endTime"
//                                name="endTime"
//                                placeholder="endTime"
//                                required
//                                onChange={handleChange}
//                         />
//                     </div>
//                     <button id="submit" type="submit" className="btn btn-dark">Submit</button>
//                 </form>
//             </div>
//         </div>
//     )
// }
//
// export default ScheduleMovie;