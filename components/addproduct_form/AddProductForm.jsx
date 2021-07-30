import React, { useState } from 'react';
import { Form, Container, Button } from 'react-bootstrap';
import TextField from './../textfield/TextField';
import Creatable from 'react-select/creatable';
import { addProduct } from './../../services/product_service/ProductService';
import { InputTags } from 'react-bootstrap-tagsinput'
import 'react-bootstrap-tagsinput/dist/index.css'
import './AddProductForm.css';

const categories = [
    { label: "Groceries", value: 1 },
    { label: "Books", value: 2 },
    { label: "Clothes", value: 3 },
    { label: "Medicines", value: 4 },
    { label: "Fruits", value: 5 },
    { label: "Miscellaneous", value: 6 }
];
const varients = {
    "Groceries": ["Standard"],
    "Books": ["Paperback","HardCover","Kindle"],
    "Clothes": ["S","M","L","XL"],
    "Medicines": ["Standard"],
    "Fruits": ["Standard"],
    "Miscellaneous": ["Standard"]
}
const AddProductForm=(props)=> {

    const [data, setData] = useState({ name: "", desc: "", category: "", tag:"", price: "", quantity: "", imageLinks: "", videoLinks: "", pdfLink: "" });
    const [errors, setErrors] = useState({});
    const [categoryValue, setCategoryValue] = useState('');
    const [currentVarient, setCurrentVarient] = useState([]);
    const [tags, setTags] = useState([])
    const changeHandler=(ce)=>{
        setData({ ...data, [ce.target.id]: ce.target.value });
        }
    var isValid = false;
    const addHandler=(e)=> {
        isValid=true;
        setErrors(validate());
        if (!isValid) {
            e.preventDefault();
        } else {
            addProduct(data);
        }
    }

    const validate=() =>{
        const { name, desc, category, tag, price, quantity, imageLinks, videoLinks, pdfLink } = data;
        const newErrors = {};
        if (!name) {
            isValid = false;
            newErrors.name = "Please enter product name";
        }
        if (!desc) {
            isValid = false;
            newErrors.desc = "Please enter description for the product";
        }
        if (!category) {
            isValid = false;
            newErrors.category = "Please enter category of the product";
        }
        if (!tag) {
            isValid = false;
            newErrors.tag = "Please enter tags of the product";
        }
        if (!price) {
            isValid = false;
            newErrors.price = "Please enter price for the product";
        }

        if (!quantity) {
            isValid = false;
            newErrors.quantity = "Please enter quantity of the product";
        }

        if (!imageLinks) {
            isValid = false;
            newErrors.imageLinks = "Please enter atleast one image link";
        }

        if (typeof price !== "undefined" && !newErrors.price) {

            var pattern = new RegExp(/^[0-9\b]+$/);
            if (!pattern.test(price)) {
                isValid = false;
                newErrors.price = "Please enter only digits";
            }
        }
        if (typeof quantity !== "undefined" && !newErrors.quantity) {

            var pat = new RegExp(/^[0-9\b]+$/);
            if (!pat.test(quantity)) {
                isValid = false;
                newErrors.quantity = "Please enter only digits";
            }
        }

        const validateImageLinks=(link)=> {
            var pat = new RegExp(/(http(s?):)([/|.|\w|\s|-])*\.(?:jpg|gif|png)/i);
            if (!pat.test(link)) {
                isValid = false;
                newErrors.imageLinks = "Invalid Image Link";
            }
        }

        if (!newErrors.imageLinks) {
            var imageLinksArray = imageLinks.split(',');
            imageLinksArray = imageLinksArray.map(link => link.trim());
            imageLinksArray.forEach(link => validateImageLinks(link));
        }

        const validateVideoLinks =(link)=> {
            console.log(link);
            pat = new RegExp(/^((?:https?:)?\/\/)?((?:www|m)\.)?((?:youtube\.com|youtu.be))(\/(?:[\w-]+\?v=|embed\/|v\/)?)([\w-]+)(\S+)?$/i);
            if (!pat.test(link)) {
                isValid = false;
                newErrors.videoLinks = "Invalid Video Link";
            }
        }

        if (videoLinks){
            var videoLinksArray = videoLinks.split(',');
            videoLinksArray = videoLinksArray.map(link => link.trim());
            videoLinksArray.forEach(link => validateVideoLinks(link));
        }




        if (pdfLink) {
            pat = new RegExp(/(http(s?):)([/|.|\w|\s|-])*\.(?:pdf)/i);
            if (!pat.test(pdfLink)) {
                isValid = false;
                newErrors.pdfLink = "Invalid PDF Link";
            }
        }

        return newErrors;

    }

    const handleChange=(value) =>{
        console.log(currentVarient);
        setCurrentVarient([]);
        if (value){
            setCategoryValue(value);
            setData({ ...data, 'category': value.label });
        }else{
            setData({ ...data, 'category': '' });
        }
    }
    const handleTags = (value) =>{
        setTags(value);
        if (value.length!==0)
            setData({ ...data, 'tag': 'tags' });
        else
            setData({ ...data, 'tag': '' });
    }
    const handleVarients = (e) => {
        setCurrentVarient([...currentVarient,e.target.value]);
    }
    return (
        <div className="mx-auto">
            <Form>
                <Container className="formCenter">
                    <div>
                        <TextField
                            id="name"
                            name="Product Name"
                            placeholder="Enter Product Name"
                            input={changeHandler}
                            isInvalid={!!errors.name}
                            error={errors.name}
                        />
                        <Form.Group className="mb-3 required">
                            <Form.Label className="control-label">Product Description</Form.Label>
                            <Form.Control
                                id="desc"
                                as="textarea"
                                placeholder="Enter Product Description"
                                style={{ height: '100px' }}
                                onChange={(e) => changeHandler(e)}
                                isInvalid={!!errors.desc}
                            />
                            <Form.Control.Feedback type='invalid'>{errors.desc}</Form.Control.Feedback>
                        </Form.Group>

                        <Form.Group className="mb-3 required">
                            <Form.Label className="control-label">Category</Form.Label>
                            <Creatable
                                id="category"
                                isClearable
                                onChange={(value) => handleChange(value)}
                                options={categories}
                                value={categoryValue}

                            />
                            {categoryValue.label!==undefined && varients[categoryValue.label].map((type) => (
                                <div key={`${type}`} className="mb-3">
                                <Form.Check 
                                    type="checkbox"
                                    id={`${type}`}
                                    label={`${type}`}
                                    value={`${type}`}
                                    onChange={handleVarients}
                                />
                                </div>
                            ))}
                            <Form.Control className='zeroheight'
                                isInvalid={!!errors.category}
                            />
                            <Form.Control.Feedback type='invalid'>{errors.category}</Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-3 required">
                            <Form.Label className="control-label">Tags</Form.Label>
                            <InputTags 
                                values={tags} 
                                placeholder="Space to add tags"
                                onTags={(value) => handleTags(value.values)} 
                            />
                            <Form.Control className='zeroheight'
                                isInvalid={!!errors.tag}
                            />
                            <Form.Control.Feedback type='invalid'>{errors.tag}</Form.Control.Feedback>
                        </Form.Group>

                        <TextField
                            id="price"
                            name="Price"
                            placeholder="Enter Price"
                            input={changeHandler}
                            isInvalid={!!errors.price}
                            error={errors.price}
                        />

                        <TextField
                            id="quantity"
                            name="Quantity"
                            placeholder="Enter the Quantity"
                            input={changeHandler}
                            isInvalid={!!errors.quantity}
                            error={errors.quantity}
                        />
                        

                        <TextField
                            id="imageLinks"
                            name="Image Links"
                            placeholder="Enter Image Links (Use comma for multiple Links)"
                            input={changeHandler}
                            isInvalid={!!errors.imageLinks}
                            error={errors.imageLinks}
                        />

                        <TextField
                            id="videoLinks"
                            name="Video Links"
                            placeholder="Enter Youtube Video Links  (Use comma for multiple Links)"
                            input={changeHandler}
                            isInvalid={!!errors.videoLinks}
                            error={errors.videoLinks}
                        />
                        <TextField
                            name="PDF Link"
                            placeholder="Enter PDF Link"
                            id="pdfLink"
                            input={changeHandler}
                            isInvalid={!!errors.pdfLink}
                            error={errors.pdfLink}
                        />
                        <Button className="custom-btn" type="submit" onClick={addHandler}>
                            Add Product
                        </Button>
                    </div>
                </Container>

            </Form>
        </div>
    );
}

export default AddProductForm;