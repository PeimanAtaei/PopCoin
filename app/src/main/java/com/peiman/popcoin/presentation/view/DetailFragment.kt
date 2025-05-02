package com.peiman.popcoin.presentation.view


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.peiman.popcoin.R
import com.peiman.popcoin.databinding.FragmentDetailBinding
import com.peiman.popcoin.presentation.viewModel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.github.mikephil.charting.data.Entry
import com.peiman.popcoin.data.model.chart.PriceChartModel


class DetailFragment : Fragment() {

    companion object {
        private const val TAG = "DetailFragment"
    }

    private lateinit var detailBinding: FragmentDetailBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var coinId: String
    private lateinit var chart: LineChart

    val options = RequestOptions()
        .fitCenter()
        .placeholder(R.drawable.coin)
        .error(R.drawable.coin)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailBinding = FragmentDetailBinding.bind(view)
        mainViewModel = (activity as MainActivity).mainViewModel

        setObservers()

        // Handle back navigation
        detailBinding.detailTopAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }

        // Retrieve the coin ID passed from the previous fragment
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        coinId = args.id

        // Fetch coin details and price chart data
        mainViewModel.getDetail(coinId)
        mainViewModel.getPriceChart(coinId,"eur",7,"daily")

        // Back navigation gesture handling
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }

    }

    // Set up observers for ViewModel LiveData
    private fun setObservers() {
        mainViewModel.coinDetail.observe(viewLifecycleOwner) {

            if (it.data!=null){
                detailBinding.detailLoading.visibility = View.GONE
                detailBinding.detailLayout.visibility = View.VISIBLE

                // Set the coin details to the UI
                val detail = it.data
                Glide.with(requireActivity().application)
                    .load(detail.image.large)
                    .apply(options)
                    .into(detailBinding.detailImage)

                detailBinding.detailSymbol.text = detail.symbol
                detailBinding.detailName.text = detail.name
                detailBinding.detailPrice.text = getPrice(detail.market_data.current_price.toString())
                detailBinding.detailHigh.text = getPrice(detail.market_data.high_24h.toString())
                detailBinding.detailLow.text = getPrice(detail.market_data.low_24h.toString())
                detailBinding.detailWeb.text = detail.links.homepage.firstOrNull() ?: "N/A"
                detailBinding.detailDescription.text = detail.description.toString()
            }
        }

        mainViewModel.priceChart.observe(viewLifecycleOwner) {
            if (it.data!=null){
                createChart(it.data)
            }
        }
    }

    // other methods ===============================================================================
    // Setup and display the chart
    private fun createChart(priceChartModel: PriceChartModel){
        chart = detailBinding.detailChart

        val priceData = convertToPriceData(priceChartModel)

        val entries = priceData.mapIndexed { index, (timestamp, price) ->
            Entry(index.toFloat(), price.toFloat())
        }

        val labels = priceData.map { (timestamp, _) ->
            val date = Date(timestamp)
            SimpleDateFormat("MM/dd", Locale.getDefault()).format(date)
        }

        val dataSet = LineDataSet(entries, "ETH Price (USD)")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 4f
        dataSet.setCircleColor(Color.BLUE)
        dataSet.setDrawValues(false)

        chart.data = LineData(dataSet)

        // Customize X Axis with date labels
        chart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt().coerceIn(labels.indices)
                    return labels[index]
                }
            }
            granularity = 1f
            labelRotationAngle = -45f
        }

        // Customize chart appearance and enable touch interactions
        chart.apply {
            axisRight.isEnabled = false
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            invalidate()
        }

        drawChart()
    }

    private fun drawChart() {

        // Chart Style
        chart = detailBinding.detailChart

        // background color
        chart.setBackgroundColor(Color.TRANSPARENT)

        // disable description text
        chart.description.isEnabled = false

        // enable touch gestures
        chart.setTouchEnabled(true)

        // set listeners
        chart.setDrawGridBackground(false)

        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        // chart.setScaleXEnabled(true)
        // chart.setScaleYEnabled(true)

        // force pinch zoom along both axis
        chart.setPinchZoom(true)
    }

    // Convert raw data from PriceChartModel to a list of pairs (timestamp, price)
    fun convertToPriceData(model: PriceChartModel): List<Pair<Long, Double>> {
        return model.prices.map { entry ->
            val timestamp = entry[0].toLong()
            val price = String.format("%.2f", entry[1]).toDouble()
            Pair(timestamp, price)
        }
    }

    // Format price string
    private fun getPrice(price: String): String{
        val jsonString = price
        val regex = Regex("""eur=([\d.]+)""")
        val matchResult = regex.find(jsonString)
        val price = matchResult?.groupValues?.get(1)?.toDouble()
        return price.toString()
    }

}