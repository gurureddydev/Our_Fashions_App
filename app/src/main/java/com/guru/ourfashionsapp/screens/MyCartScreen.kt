package com.guru.ourfashionsapp.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.ourfashionsapp.R

@Composable
fun MyCartScreen(
    name: String,
    price: String,
    des: String,
    image: Painter,
) {
    var quantity by remember { mutableStateOf(1) }
    var promoCode by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "My Cart", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TopBar()
        Spacer(modifier = Modifier.height(16.dp))

        CartItem(
            imageResId = image, // Replace with your image resource
            title = name,
            description = des,
            price = price,
        )

        Spacer(modifier = Modifier.height(36.dp))

        PromoCodeField(
            promoCode = promoCode,
            onPromoCodeChange = { promoCode = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        CheckoutSummary(totalPrice = 198.00 * quantity)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            onClick = { navigateToRazorpay(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(text = "Proceed to Checkout")
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(5.dp))
                        .size(24.dp) // Adjust size as needed
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.Black, // Adjust tint as needed
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

fun navigateToRazorpay(context: Context) {
    val razorpayUrl = "https://rzp.io/l/nibOhHXt"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(razorpayUrl))
    context.startActivity(intent)
}

@Composable
fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.background(color = Color.Black, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_ios_24),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(18.dp),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.background(color = Color.LightGray, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.shopping_bag_24),
                contentDescription = "Favorite",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun CartItem(
    imageResId: Painter,
    title: String,
    description: String,
    price: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Image(
            painter = imageResId,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
            Text(text = "$${price}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Box(
            modifier = Modifier
                .align(Alignment.Bottom)
        ) {
            QuantitySelector(
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromoCodeField(promoCode: String, onPromoCodeChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
    ) {
        TextField(
            value = promoCode,
            onValueChange = onPromoCodeChange,
            placeholder = { Text(text = "Promo Code") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent, // Make the TextField background transparent
                focusedIndicatorColor = Color.Transparent, // Remove the indicator line
                unfocusedIndicatorColor = Color.Transparent // Remove the indicator line
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 64.dp) // Add padding to the right to make space for the button
        )

        Button(
            onClick = { /* Apply promo code action */ },
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ), modifier = Modifier
                .clip(RoundedCornerShape(10))
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp) // Adjust padding to position the button properly
        ) {
            Text(text = "Apply")
        }
    }
}


@Composable
fun CheckoutSummary(totalPrice: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(text = "Total (1 item):", fontSize = 16.sp)
        Text(text = "$${totalPrice}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}
