package com.guru.ourfashionsapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.guru.ourfashionsapp.R

@Composable
fun ProductDetailScreen(
    id: String,
    name: String,
    price: String,
    des: String,
    image: Painter,
    navController: NavHostController
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(16.dp))
        ProductImage(image)
        Spacer(modifier = Modifier.height(16.dp))
        ProductDetails(
            id, name, des, price, image, navController
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}


@Composable
fun ProductImage(image: Painter) {
    Image(
        painter = image,
        contentDescription = "Product Image",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
    )
}

@Composable
fun ProductDetails(
    id: String,
    name: String,
    des: String,
    price: String,
    image: Painter,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(30.dp))
            .background(Color.LightGray)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(
                        text = name, fontSize = 24.sp, fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = des, fontSize = 18.sp, color = Color.Black
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_11),
                            contentDescription = "Star",
                            modifier = Modifier.size(60.dp)
                        )
                        Text(
                            text = "4.5",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "(320 Reviews)", fontSize = 14.sp, color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Size",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SizeSelector()
                }

                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    QuantitySelector()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Available in stock",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ColorSelector()
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Description",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Get a little lift from these Sam Edelman sandals featuring ruched straps and leather lace-up ties, while a braided jute sole makes a fresh statement for summer.",
                fontSize = 14.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Column {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Total Price:",
                        fontSize = 14.sp,
                    )
                    Text(
                        text = price, fontSize = 22.sp, fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                AddToCartButton(id, name, des, price, image, navController)
            }
        }
    }
}

@Composable
fun QuantitySelector() {
    var quantity by remember { mutableStateOf(1) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(CircleShape)
            .background(Color(0xFFEEEEEE))
            .padding(1.dp)           // Optional: Add some padding
    ) {
        IconButton(onClick = { if (quantity > 1) quantity-- }) {
            Icon(
                painter = painterResource(id = R.drawable.remove_24),
                contentDescription = "Decrease",
                modifier = Modifier.size(14.dp)
            )
        }
        Text(text = quantity.toString(), fontSize = 14.sp)
        IconButton(onClick = { quantity++ }) {
            Icon(
                painter = painterResource(id = R.drawable.add_24),
                contentDescription = "Increase",
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun SizeSelector(
    sizes: List<String> = listOf("S", "M", "L", "XL"),
    onSizeChange: (String) -> Unit = {},
    initialState: String = "M"
) {
    var selectedSize by remember { mutableStateOf(initialState) }
    Row {
        sizes.forEach { size ->
            Surface(
                modifier = Modifier
                    .padding(3.dp)
                    .size(45.dp),
                shape = CircleShape,
                border = BorderStroke(width = 1.dp, color = Color.LightGray)
            ) {
                TextButton(
                    onClick = { selectedSize = size; onSizeChange(size) },
                    modifier = Modifier.padding(4.dp),
                    colors = /* Use contentColor for default colors */
                    ButtonDefaults.textButtonColors(
                        contentColor = if (selectedSize == size) Color.White else Color.Black,
                        containerColor = if (selectedSize == size) Color.Black else Color.White
                    )
                ) {
                    Text(text = size, fontSize = 10.sp) // No need to set color explicitly
                }
            }
        }
    }
}

@Composable
fun ColorSelector() {
    var selectedColor by remember { mutableStateOf(Color.Black) }
    val colors = listOf(Color.Black, Color.Green, Color.Gray, Color.Yellow)

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White)
            .padding(8.dp)
    ) {
        colors.forEach { color ->
            Box(modifier = Modifier
                .size(35.dp)
                .offset(x = (-1).dp)
                .padding(4.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { selectedColor = color }) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            2.dp,
                            if (selectedColor == color) Color.Black else Color.Transparent,
                            CircleShape
                        )
                        .align(Alignment.Center)
                )
                if (selectedColor == color) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Selected Color",
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AddToCartButton(
    id: String,
    name: String,
    des: String,
    price: String,
    imageResourceId: Painter,
    navController: NavController
) {
    Button(
        onClick = {
            navController.navigate("myCartScreen/$id/$name/$des/$price/$imageResourceId")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black, contentColor = Color.White
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.shopping_bag_24),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add to cart")
        }
    }
}

