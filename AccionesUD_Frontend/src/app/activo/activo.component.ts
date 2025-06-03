import { AfterViewInit, ElementRef, Component, ViewChild, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { createChart, IChartApi, CandlestickData, CandlestickSeries, ISeriesApi } from 'lightweight-charts';

@Component({
  selector: 'app-activo',
  templateUrl: './activo.component.html',
  styleUrls: ['./activo.component.css'],
})
export class ActivoComponent implements OnInit, AfterViewInit,OnChanges {

  @Input() nombreActivo!: string;
  @Input() precioCompra!: number;
  @Input() precioVenta!: number;
  @Input() pais!: string;
  @Input() chartData!: CandlestickData[];

  @ViewChild('chartContainer', { static: false }) chartContainer!: ElementRef;
  private chart!: IChartApi;
  private series!: ISeriesApi<'Candlestick'>;

  ngOnInit() {
    
  }

  ngAfterViewInit() {
    this.initializeChart();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['chartData']) {
      console.log('chartData changed');
      const prev = changes['chartData'].previousValue;
      const next = changes['chartData'].currentValue;
      console.log('prev:', prev?.length, 'next:', next?.length);
    }

    if (changes['chartData'] && !changes['chartData'].firstChange) {
      const prev = changes['chartData'].previousValue;
      const next = changes['chartData'].currentValue;

      console.log('Chart data changed:', { prev, next });

      this.updateChartData();
      
    }

  }

  initializeChart() {
    this.chart = createChart(this.chartContainer.nativeElement, {
      width: 780, 
      height: 300,
      layout: {
        background: { color: '#ffffff' },
        textColor: '#000',
      },
      rightPriceScale: { 
        borderColor: '#cccccc',
      },
      grid: {
        vertLines: { color: '#e1e1e1' },
        horzLines: { color: '#e1e1e1' },
      },
      timeScale: {
        borderColor: '#cccccc',
        timeVisible: true,
        secondsVisible: false,
      },
    });

    this.series = this.chart.addSeries(CandlestickSeries,{
      upColor: '#26a69a',
      downColor: '#ef5350',
      borderVisible: false,
      wickUpColor: '#26a69a',
      wickDownColor: '#ef5350',
    });

    this.series.setData(this.chartData);
  }

  updateChartData() {
    
    //this.series.update();
  }

}
